package com.uvg.ana.app1.locations

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.uvg.ana.app1.data.LocationDb

@Composable
fun LocationsScreen(onLocationClick: (Int) -> Unit) {
    val locations = LocationDb().getAllLocations()

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
