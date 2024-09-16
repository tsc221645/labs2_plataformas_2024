package com.uvg.ana.app1.locations

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.uvg.ana.app1.data.LocationDb
import com.uvg.ana.app1.model.Location
import java.lang.reflect.Modifier

@Composable
fun LocationsScreen(navController: NavHostController) {
    val locations = LocationDb.getAllLocations()  // Usa tu clase existente para obtener las locaciones
    LazyColumn {
        items(locations) { location ->
            LocationItem(location) {
                navController.navigate("location_details/${location.id}")
            }
        }
    }
}
