package com.uvg.ana.app1.locationdetails

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.locationDetailsNavGraph(navController: NavController) {
    composable("locationDetails/{locationId}") { backStackEntry ->
        val locationId = backStackEntry.arguments?.getString("locationId")?.toInt() ?: return@composable
        LocationDetailsScreen(locationId = locationId, onBack = { navController.popBackStack() })
    }
}
