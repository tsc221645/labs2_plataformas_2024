package com.uvg.ana.app1.locations

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.uvg.ana.app1.locationdetails.LocationDetailsScreen


fun NavGraphBuilder.locationsNavGraph(navController: NavController) {
    composable("locations") {
        LocationsScreen(
            onLocationClick = { locationId ->
                navController.navigate("locationDetails/$locationId")
            }
        )
    }
    composable("locationDetails/{locationId}") { backStackEntry ->
        val locationId = backStackEntry.arguments?.getString("locationId")?.toInt() ?: return@composable
        LocationDetailsScreen(locationId = locationId, onBack = { navController.popBackStack() })
    }
}
