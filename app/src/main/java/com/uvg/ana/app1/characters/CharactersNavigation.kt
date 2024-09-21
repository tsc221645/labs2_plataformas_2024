package com.uvg.ana.app1.characters

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.uvg.ana.app1.characterDetails.CharacterDetailScreen

fun NavGraphBuilder.charactersNavGraph(navController: NavController) {
    composable("characters") {
        CharactersScreen(
            onCharacterClick = { characterId ->
                navController.navigate("characterDetails/$characterId")
            }
        )
    }
    composable("characterDetails/{characterId}") { backStackEntry ->
        val characterId = backStackEntry.arguments?.getString("characterId")?.toInt() ?: return@composable
        CharacterDetailScreen(characterId = characterId, onBack = { navController.popBackStack() })
    }
}
