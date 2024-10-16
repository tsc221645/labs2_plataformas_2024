package com.uvg.ana.app1

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.uvg.ana.app1.characters.charactersNavGraph
import com.uvg.ana.app1.data.UserPreferences
import com.uvg.ana.app1.locations.locationsNavGraph
import com.uvg.ana.app1.profile.profileNavGraph
import com.uvg.ana.app1.bottomnavigation.BottomNavBar
import com.uvg.ana.app1.login.LoginNavigation
import com.uvg.ana.app1.login.LoginScreen
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val userPreferences = UserPreferences(this)
        setContent {
            MainScreen(userPreferences = userPreferences)
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(userPreferences: UserPreferences) {
    val navController = rememberNavController()
    var selectedItem by remember { mutableStateOf(0) }

    // Obtener el nombre del usuario desde DataStore
    val userName by userPreferences.userNameFlow.collectAsState(initial = null)

    // Determinar la pantalla inicial según si el usuario está logueado o no
    val startDestination = if (userName != null) "characters" else "login"

    Scaffold(
        bottomBar = {
            if (startDestination != "login") {
                BottomNavBar(selectedItem) { index ->
                    selectedItem = index
                    when (index) {
                        0 -> navController.navigate("characters")
                        1 -> navController.navigate("locations")
                        2 -> navController.navigate("profile")
                    }
                }
            }
        }
    ) {
        NavHost(navController = navController, startDestination = startDestination) {
            LoginNavigation(navController = navController, userPreferences = userPreferences)
            charactersNavGraph(navController)
            locationsNavGraph(navController)
            profileNavGraph(navController, userPreferences = userPreferences)
        }
    }
}
