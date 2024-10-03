package com.uvg.ana.app1.locationdetails

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.uvg.ana.app1.data.LocationDb
import com.uvg.ana.app1.model.Location
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class LocationDetailState(
    val isLoading: Boolean = false,
    val data: Location? = null,
    val hasError: Boolean = false
)

class LocationDetailViewModel(private val locationId: Int) : ViewModel() {

    private val _state = MutableStateFlow(LocationDetailState(isLoading = true))
    val state: StateFlow<LocationDetailState> = _state

    init {
        loadLocation()
    }

    private fun loadLocation() {
        if (locationId == -1) {
            _state.value = LocationDetailState(isLoading = false, hasError = true)
            return
        }

        viewModelScope.launch {
            _state.value = LocationDetailState(isLoading = true)
            delay(2000) // Simulación del tiempo de carga de datos (2 segundos)

            try {
                val location = LocationDb().getLocationById(locationId)
                if (location != null) {
                    _state.value = LocationDetailState(isLoading = false, data = location, hasError = false)
                } else {
                    Log.d("LocationDetailViewModel", "No se encontró la ubicación con ID: $locationId")
                    _state.value = LocationDetailState(isLoading = false, hasError = true)
                }
            } catch (e: Exception) {
                Log.e("LocationDetailViewModel", "Error al cargar los detalles de la ubicación", e)
                _state.value = LocationDetailState(isLoading = false, hasError = true)
            }
        }
    }

    fun setError() {
        _state.value = _state.value.copy(isLoading = false, hasError = true)
    }

    fun retryLoading() {
        Log.d("LocationDetailViewModel", "Reintentando cargar la ubicación")
        loadLocation()
    }

    companion object {
        fun provideFactory(locationId: Int): ViewModelProvider.Factory {
            return object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    if (modelClass.isAssignableFrom(LocationDetailViewModel::class.java)) {
                        return LocationDetailViewModel(locationId) as T
                    }
                    throw IllegalArgumentException("Unknown ViewModel class")
                }
            }
        }
    }
}
