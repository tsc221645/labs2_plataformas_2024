package com.uvg.ana.app1.profile

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.uvg.ana.app1.R
import com.uvg.ana.app1.data.UserPreferences
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    userPreferences: UserPreferences,
    navController: NavController
) {
    val userName by userPreferences.userNameFlow.collectAsState(initial = "")
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Profile") }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(40.dp))

            // Profile Image
            Image(
                painter = painterResource(id = R.drawable.profilepic),
                contentDescription = "Profile Image",
                modifier = Modifier
                    .size(150.dp)
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Mostrar el nombre del usuario desde DataStore
            Text("Nombre:", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            userName?.let { it1 -> Text(it1, fontSize = 18.sp) }

            Spacer(modifier = Modifier.height(8.dp))

            //Text("Carné:", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            //Text("221645", fontSize = 18.sp)

            Spacer(modifier = Modifier.height(24.dp))

            // Logout Button
            Button(
                onClick = {
                    coroutineScope.launch {
                        userPreferences.clearUserName()
                        // Navegar de regreso a la pantalla de login
                        navController.navigate("login") {
                            popUpTo(navController.graph.startDestinationId) { inclusive = true }
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .height(50.dp)
            ) {
                Text("Cerrar sesión", fontSize = 16.sp)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewProfileScreen() {
    // Esto es solo para el modo de vista previa
}
