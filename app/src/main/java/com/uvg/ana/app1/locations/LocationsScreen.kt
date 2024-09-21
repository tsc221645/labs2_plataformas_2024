package com.uvg.ana.app1.locations

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.uvg.ana.app1.data.LocationDb

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationsScreen(onLocationClick: (Int) -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Locations") }
            )
        }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            val locations = LocationDb().getAllLocations()
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
}
