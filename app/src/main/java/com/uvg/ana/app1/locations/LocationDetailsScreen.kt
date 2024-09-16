package com.uvg.ana.app1.locations

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.uvg.ana.app1.data.LocationDb

@Composable
fun LocationDetailsScreen(locationId: Int) {
    val location = LocationDb.getLocationById(locationId)
    Column {
        Text("ID: ${location.id}")
        Text("Name: ${location.name}")
        Text("Type: ${location.type}")
        Text("Dimension: ${location.dimension}")
    }
}
