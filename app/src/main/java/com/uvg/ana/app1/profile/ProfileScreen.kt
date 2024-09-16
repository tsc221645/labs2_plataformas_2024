package com.uvg.ana.app1.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.uvg.ana.app1.R

@Composable
fun ProfileScreen(navController: NavHostController) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painterResource(id = R.drawable.logo_rick_morty), contentDescription = "Profile Image")
        Text("Nombre: Juan Carlos Durini")
        Text("Carné: 1201613")
        Button(onClick = {
            navController.popBackStack(navController.graph.startDestinationId, false)
        }) {
            Text("Cerrar sesión")
        }
    }
}
