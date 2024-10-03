package com.uvg.ana.app1.locationdetails

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

import com.uvg.ana.app1.characters.ErrorLayout
import com.uvg.ana.app1.characters.LoadingLayout
import com.uvg.ana.app1.model.Location

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LocationDetailsScreen(
    locationId: Int,
    onBack: () -> Unit
) {
    val viewModel: LocationDetailViewModel = viewModel(
        factory = LocationDetailViewModel.provideFactory(locationId)
    )

    val state by viewModel.state.collectAsState()

    Log.d("LocationDetailsScreen", "Current State: isLoading=${state.isLoading}, hasError=${state.hasError}, data=${state.data}")

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Location Details") },
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
                    LocationDetailContent(location = state.data!!)
                }
                else -> {
                    ErrorLayout(onRetry = { viewModel.retryLoading() })
                }
            }
        }
    }
}

@Composable
fun LocationDetailContent(location: Location) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        // Mostrar los detalles de la ubicación
        Text("Name: ${location.name}", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(8.dp))

        Text("ID: ${location.id}", style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(8.dp))

        Text("Type: ${location.type}", style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(8.dp))

        Text("Dimensions: ${location.dimension}", style = MaterialTheme.typography.bodyMedium)
    }
}
