package com.uvg.ana.app1.characterDetails

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.uvg.ana.app1.model.Character
import coil.compose.rememberImagePainter
import com.uvg.ana.app1.characters.ErrorLayout
import com.uvg.ana.app1.characters.LoadingLayout

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CharacterDetailScreen(
    characterId: Int,
    onBack: () -> Unit
) {
    val viewModel: CharacterDetailViewModel = viewModel(
        factory = CharacterDetailViewModel.provideFactory(characterId)
    )

    val state by viewModel.state.collectAsState()

    Log.d("CharacterDetailScreen", "Current State: isLoading=${state.isLoading}, hasError=${state.hasError}, data=${state.data}")

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Character Details") },
                navigationIcon = {
                    IconButton(onClick = { onBack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
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
                    CharacterDetailContent(character = state.data!!)
                }
                else -> {
                    ErrorLayout(onRetry = { viewModel.retryLoading() })
                }
            }
        }
    }
}

@Composable
fun CharacterDetailContent(character: Character) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        // Imagen del personaje
        Image(
            painter = rememberImagePainter(character.imageUrl),
            contentDescription = character.name,
            modifier = Modifier
                .size(150.dp)
                .clip(MaterialTheme.shapes.medium)
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Detalles del personaje
        Text("Name: ${character.name}", style = MaterialTheme.typography.titleLarge)
        Text("Species: ${character.species}")
        Text("Status: ${character.status}")
        Text("Gender: ${character.gender}")
    }
}
