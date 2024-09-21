package com.uvg.ana.app1.locationdetails

import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.uvg.ana.app1.data.LocationDb

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationDetailsScreen(locationId: Int, onBack: () -> Unit) {
    // Instancia de LocationDb
    val locationDb = LocationDb()

    // Obtenemos la ubicación
    val location = locationDb.getLocationById(locationId)

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
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // Mostramos la información de la ubicación
            Text("Name: ${location.name}", style = MaterialTheme.typography.titleLarge)
            Text("ID: ${location.id}")
            Text("Type: ${location.type}")
            Text("Dimensions: ${location.dimension}")
        }
    }
}
