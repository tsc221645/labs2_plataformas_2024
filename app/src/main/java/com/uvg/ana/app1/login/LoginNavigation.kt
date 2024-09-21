package com.uvg.ana.app1.login

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable


fun NavGraphBuilder.LoginNavigation(navController: NavController) {
    composable("login") {
        LoginScreen(onLoginSuccess = {
            // Aquí navegamos hacia la pantalla de personajes
            navController.navigate("characters") {
                popUpTo("login") { inclusive = true } // Eliminar login del backstack
            }
        })
    }
}
