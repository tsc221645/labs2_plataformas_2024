package com.uvg.rickandmorty.presentation.mainFlow.character.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.uvg.rickandmorty.data.model.Character
import com.uvg.rickandmorty.presentation.common.ErrorView
import com.uvg.rickandmorty.presentation.common.LoadingView

@Composable
fun CharacterListRoute(
    onCharacterClick: (Int) -> Unit,
    viewModel: CharacterListViewModel = viewModel(factory = CharacterListViewModel.Factory)
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    CharacterListScreen(
        state = state,
        forceError = { viewModel.onEvent(CharacterListEvent.ForceError) },
        onRetryClick = { viewModel.onEvent(CharacterListEvent.RetryClick) },
        onCharacterClick = onCharacterClick,
        modifier = Modifier.fillMaxSize()
    )
}

@Composable
fun CharacterListScreen(
    state: CharacterListState,
    forceError: () -> Unit,
    onRetryClick: () -> Unit,
    onCharacterClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier) {
        when {
            state.isLoading -> {
                LoadingView(
                    loadingText = "Obteniendo personajes",
                    modifier = Modifier
                        .align(Alignment.Center)
                        .clickable { forceError() }
                )
            }

            state.isError -> {
                ErrorView(
                    errorText = "Error al obtener personajes",
                    onRetryClick = onRetryClick,
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }

            else -> {
                LazyColumn(
                    modifier = modifier
                ) {
                    items(state.characters) { item ->
                        CharacterItem(
                            character = item,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { onCharacterClick(item.id) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CharacterItem(
    character: Character,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Aquí podrías agregar una imagen si quieres
        Column {
            Text(text = character.name, style = MaterialTheme.typography.bodyLarge)
            Text(
                text = "${character.species} - ${character.status}",
                style = MaterialTheme.typography.labelSmall
            )
        }
    }
}
