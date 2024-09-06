package com.uvg.ana.app1.characterDetails

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.NavBackStackEntry

fun NavGraphBuilder.CharacterDetailNavigation(navController: NavController) {
    composable("characterDetails/{characterId}") { backStackEntry: NavBackStackEntry ->
        val characterId = backStackEntry.arguments?.getString("characterId")?.toIntOrNull()
        characterId?.let {
            CharacterDetailScreen(characterId = it, onBack = { navController.popBackStack() })
        }
    }
}
