package com.uvg.ana.app1.locations

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.uvg.ana.app1.model.Location

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LocationsScreen(
    onLocationClick: (Int) -> Unit,
    viewModel: LocationsViewModel = viewModel()
) {
    val state by viewModel.state.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Locations") })
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
                    LocationList(state.data!!, onLocationClick)
                }
                else -> {
                    // Manejar cualquier otro estado inesperado
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
            .clickable { onClick() } // Hacemos que el layout sea clickable para establecer el error
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
            Icon(
                Icons.Default.Warning,
                contentDescription = "Error Icon",
                modifier = Modifier.size(48.dp),
                tint = MaterialTheme.colorScheme.error
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text("Error al obtener ubicaciones. Intenta de nuevo.", color = MaterialTheme.colorScheme.error)
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = onRetry) {
                Text("Reintentar")
            }
        }
    }
}

@Composable
fun LocationList(locations: List<Location>, onLocationClick: (Int) -> Unit) {
    Column(modifier = Modifier.padding(16.dp)) {
        locations.forEach { location ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable { onLocationClick(location.id) }
            ) {
                Text("Name: ${location.name}", modifier = Modifier.weight(1f))
                Text("Type: ${location.type}")
            }
        }
    }
}
