package com.uvg.ana.app1.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uvg.ana.app1.data.CharacterDb
import com.uvg.ana.app1.model.Character
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class CharactersState(
    val isLoading: Boolean = false,
    val data: List<Character>? = null,
    val hasError: Boolean = false
)

class CharactersViewModel : ViewModel() {

    private val _state = MutableStateFlow(CharactersState(isLoading = true))
    val state: StateFlow<CharactersState> = _state

    init {
        loadCharacters()
    }

    private fun loadCharacters() {
        viewModelScope.launch {
            _state.value = CharactersState(isLoading = true)
            delay(4000) // Simulamos el tiempo de espera de 4 segundos

            try {
                val characters = CharacterDb.getAllCharacters()
                if (characters.isNotEmpty()) {
                    _state.value = CharactersState(isLoading = false, data = characters)
                } else {
                    _state.value = CharactersState(isLoading = false, hasError = true)
                }
            } catch (e: Exception) {
                _state.value = CharactersState(isLoading = false, hasError = true)
            }
        }
    }

    fun setError() {
        _state.value = _state.value.copy(isLoading = false, hasError = true)
    }

    fun retryLoading() {
        _state.value = CharactersState(isLoading = true)
        loadCharacters()
    }
}
