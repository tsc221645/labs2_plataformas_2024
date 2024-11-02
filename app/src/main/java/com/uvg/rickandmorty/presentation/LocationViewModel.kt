package com.uvg.rickandmorty.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uvg.rickandmorty.data.local.entity.LocationEntity
import com.uvg.rickandmorty.data.repository.LocationRepository
import kotlinx.coroutines.launch

class LocationViewModel(private val repository: LocationRepository) : ViewModel() {

    val locations = mutableListOf<LocationEntity>()

    fun loadLocations() {
        viewModelScope.launch {
            locations.addAll(repository.getLocations())
        }
    }
}
