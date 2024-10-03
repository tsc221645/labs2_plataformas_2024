package com.uvg.ana.app1.characterDetails

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.uvg.ana.app1.data.CharacterDb
import com.uvg.ana.app1.model.Character
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class CharacterDetailState(
    val isLoading: Boolean = false,
    val data: Character? = null,
    val hasError: Boolean = false
)

class CharacterDetailViewModel(private val characterId: Int) : ViewModel() {

    private val _state = MutableStateFlow(CharacterDetailState(isLoading = true))
    val state: StateFlow<CharacterDetailState> = _state

    init {
        loadCharacter()
    }

    private fun loadCharacter() {
        if (characterId == -1) {
            _state.value = CharacterDetailState(isLoading = false, hasError = true)
            return
        }

        viewModelScope.launch {
            _state.value = CharacterDetailState(isLoading = true)
            delay(2000) // Simulación del tiempo de carga de datos (2 segundos)

            try {
                val character = CharacterDb.getCharacterById(characterId)
                if (character != null) {
                    _state.value = CharacterDetailState(isLoading = false, data = character, hasError = false)
                } else {
                    Log.d("CharacterDetailViewModel", "No se encontró el personaje con ID: $characterId")
                    _state.value = CharacterDetailState(isLoading = false, hasError = true)
                }
            } catch (e: Exception) {
                Log.e("CharacterDetailViewModel", "Error al cargar los detalles del personaje", e)
                _state.value = CharacterDetailState(isLoading = false, hasError = true)
            }
        }
    }

    fun setError() {
        _state.value = _state.value.copy(isLoading = false, hasError = true)
    }

    fun retryLoading() {
        Log.d("CharacterDetailViewModel", "Reintentando cargar el personaje")
        loadCharacter()
    }

    companion object {
        fun provideFactory(characterId: Int): ViewModelProvider.Factory {
            return object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    if (modelClass.isAssignableFrom(CharacterDetailViewModel::class.java)) {
                        return CharacterDetailViewModel(characterId) as T
                    }
                    throw IllegalArgumentException("Unknown ViewModel class")
                }
            }
        }
    }
}
