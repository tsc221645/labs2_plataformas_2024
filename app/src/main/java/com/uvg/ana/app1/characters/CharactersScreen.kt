package com.uvg.ana.app1.characters

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter

import com.uvg.ana.app1.model.Character

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CharactersScreen(onCharacterClick: (Int) -> Unit, viewModel: CharactersViewModel = viewModel()) {
    val state by viewModel.state.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Characters") })
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            when {
                state.isLoading -> {
                    LoadingLayout(onClick = { viewModel.setError() })
                }
                state.hasError -> {
                    ErrorLayout(onRetry = { viewModel.retryLoading() })
                }
                state.data != null -> {
                    CharacterList(state.data!!, onCharacterClick)
                }
                else -> {
                    ErrorLayout(onRetry = { viewModel.retryLoading() })
                }
            }
        }
    }
}

@Composable
fun LoadingLayout(onClick: () -> Unit) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .clickable { onClick() } // Al hacer clic en la pantalla de carga se cambia al estado de error
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            CircularProgressIndicator()
            Spacer(modifier = Modifier.height(16.dp))
            Text("Cargando")
        }
    }
}

@Composable
fun ErrorLayout(onRetry: () -> Unit) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Error al obtener personajes. Intenta de nuevo.")
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = onRetry) {
                Text("Reintentar")
            }
        }
    }
}

@Composable
fun CharacterList(characters: List<Character>, onCharacterClick: (Int) -> Unit) {
    Column(modifier = Modifier.padding(16.dp)) {
        characters.forEach { character ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable { onCharacterClick(character.id) }
            ) {
                Image(
                    painter = rememberImagePainter(character.imageUrl),
                    contentDescription = character.name,
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(text = character.name, style = MaterialTheme.typography.titleLarge)
                    Text(text = "${character.species} - ${character.status}")
                }
            }
        }
    }
}
