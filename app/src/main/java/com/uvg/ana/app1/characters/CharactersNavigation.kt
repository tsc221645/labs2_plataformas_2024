package com.uvg.ana.app1.characters

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.CharactersNavigation(navController: NavController) {
    composable("characters") {
        CharactersScreen(
            selectedItem = 0, // Asegúrate de pasar el valor que corresponda aquí
            onCharacterClick = { characterId ->
                navController.navigate("characterDetails/$characterId")
            },
            onNavItemSelected = { selectedItem ->
                // Maneja la navegación según el item seleccionado
            }
        )
    }
}
