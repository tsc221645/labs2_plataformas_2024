package com.uvg.ana.app1.profile

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.uvg.ana.app1.data.UserPreferences

fun NavGraphBuilder.profileNavGraph(
    navController: NavController,
    userPreferences: UserPreferences
) {
    composable("profile") {
        ProfileScreen(
            userPreferences = userPreferences,
            navController = navController
        )
    }
}
