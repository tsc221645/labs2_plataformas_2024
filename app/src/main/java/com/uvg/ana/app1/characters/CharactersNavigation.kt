package com.uvg.ana.app1.characters

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.CharactersNavigation(navController: NavController) {
    composable("characters") {
        CharactersScreen(
            onCharacterClick = { characterId ->
                navController.navigate("characterDetails/$characterId")
            }
        )
    }
}
