package com.uvg.ana.app1

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.uvg.ana.app1.characters.charactersNavGraph
import com.uvg.ana.app1.locations.locationsNavGraph
import com.uvg.ana.app1.profile.profileNavGraph
import com.uvg.ana.app1.bottomnavigation.BottomNavBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    var selectedItem by remember { mutableStateOf(0) }

    Scaffold(
        bottomBar = {
            BottomNavBar(selectedItem) { index ->
                selectedItem = index
                when (index) {
                    0 -> navController.navigate("characters")
                    1 -> navController.navigate("locations")
                    2 -> navController.navigate("profile")
                }
            }
        }
    ) {
        NavHost(navController = navController, startDestination = "characters") {
            charactersNavGraph(navController)
            locationsNavGraph(navController)
            profileNavGraph(navController)
        }
    }
}
