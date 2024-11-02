package com.uvg.rickandmorty.data.repository

import com.uvg.rickandmorty.data.local.dao.LocationDao
import com.uvg.rickandmorty.data.local.entity.LocationEntity
import com.uvg.rickandmorty.data.remote.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LocationRepository(private val locationDao: LocationDao) {

    suspend fun getLocations(): List<LocationEntity> {
        return withContext(Dispatchers.IO) {
            try {
                val locationsFromApi = RetrofitInstance.api.getLocations().results.map { dto ->
                    LocationEntity(dto.id, dto.name, dto.type, dto.dimension)
                }
                locationDao.insertAll(locationsFromApi)
                locationsFromApi
            } catch (e: Exception) {
                // Si falla, obtenemos desde Room
                locationDao.getAllLocations()
            }
        }
    }

    suspend fun getLocationById(id: Int): LocationEntity {
        return locationDao.getLocationById(id)
    }
}
