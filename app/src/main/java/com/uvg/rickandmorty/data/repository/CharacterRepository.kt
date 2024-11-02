package com.uvg.rickandmorty.data.repository

import com.uvg.rickandmorty.data.local.dao.CharacterDao
import com.uvg.rickandmorty.data.local.entity.CharacterEntity
import com.uvg.rickandmorty.data.remote.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CharacterRepository(private val characterDao: CharacterDao) {

    suspend fun getCharacters(): List<CharacterEntity> {
        return withContext(Dispatchers.IO) {
            try {
                // Llamar al API para obtener la lista de personajes
                val charactersFromApi = RetrofitInstance.api.getCharacters().results.map { dto ->
                    CharacterEntity(
                        id = dto.id,
                        name = dto.name,
                        status = dto.status,
                        species = dto.species,
                        gender = dto.gender,  // Aquí incluimos el campo 'gender'
                        image = dto.image
                    )
                }
                // Guardar los personajes en Room
                characterDao.insertAll(charactersFromApi)
                charactersFromApi
            } catch (e: Exception) {
                // Si ocurre un error, obtener los personajes desde Room
                characterDao.getAllCharacters()
            }
        }
    }

    suspend fun getCharacterById(id: Int): CharacterEntity {
        return characterDao.getCharacterById(id)
    }
}
