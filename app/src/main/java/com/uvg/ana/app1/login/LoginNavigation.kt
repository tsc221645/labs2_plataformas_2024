package com.uvg.ana.app1.login

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.uvg.ana.app1.data.UserPreferences

fun NavGraphBuilder.LoginNavigation(
    navController: NavController,
    userPreferences: UserPreferences
) {
    composable("login") {
        LoginScreen(
            userPreferences = userPreferences,
            onLoginSuccess = {
                // Navegamos hacia la pantalla de personajes
                navController.navigate("characters") {
                    popUpTo("login") { inclusive = true } // Eliminar login del backstack
                }
            }
        )
    }
}
