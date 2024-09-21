package com.uvg.ana.app1.profile

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.profileNavGraph(navController: NavController) {
    composable("profile") {
        ProfileScreen(onLogoutClick = {
            navController.navigate("login") {
                popUpTo(navController.graph.startDestinationId) { inclusive = true }
            }
        })
    }
}
