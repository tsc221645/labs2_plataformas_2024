package com.uvg.rickandmorty.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uvg.rickandmorty.data.local.entity.CharacterEntity
import com.uvg.rickandmorty.data.repository.CharacterRepository
import kotlinx.coroutines.launch

class CharacterViewModel(private val repository: CharacterRepository) : ViewModel() {

    val characters = mutableListOf<CharacterEntity>()

    fun loadCharacters() {
        viewModelScope.launch {
            characters.addAll(repository.getCharacters())
        }
    }
}
