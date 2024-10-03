package com.uvg.ana.app1.locations

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uvg.ana.app1.data.LocationDb
import com.uvg.ana.app1.model.Location
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class LocationsState(
    val isLoading: Boolean = false,
    val data: List<Location>? = null,
    val hasError: Boolean = false
)

class LocationsViewModel : ViewModel() {

    private val _state = MutableStateFlow(LocationsState(isLoading = true))
    val state: StateFlow<LocationsState> = _state

    init {
        loadLocations()
    }

    private fun loadLocations() {
        viewModelScope.launch {
            _state.value = LocationsState(isLoading = true)
            delay(4000) // Simulamos el tiempo de espera de 4 segundos

            try {
                val locations = LocationDb().getAllLocations()
                Log.d("LocationsViewModel", "Locations Loaded: ${locations.size} items")

                if (locations.isNotEmpty()) {
                    _state.value = LocationsState(isLoading = false, data = locations)
                } else {
                    Log.d("LocationsViewModel", "No locations found, setting error state.")
                    _state.value = LocationsState(isLoading = false, hasError = true)
                }
            } catch (e: Exception) {
                Log.e("LocationsViewModel", "Error while loading locations", e)
                _state.value = LocationsState(isLoading = false, hasError = true)
            }
        }
    }

    fun setError(){
        _state.value = _state.value.copy(isLoading = false, hasError = true)
    }

    fun retryLoading() {
        Log.d("LocationsViewModel", "Retry loading locations")
        _state.value = LocationsState(isLoading = true)
        loadLocations()
    }
}
