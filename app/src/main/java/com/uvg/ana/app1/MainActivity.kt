package com.uvg.ana.app1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.uvg.ana.app1.characterDetails.CharacterDetailNavigation
import com.uvg.ana.app1.characters.CharactersNavigation
import com.uvg.ana.app1.login.LoginNavigation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = "login"
            ) {
                LoginNavigation(navController)
                CharactersNavigation(navController)
                CharacterDetailNavigation(navController)
            }
        }
    }
}
