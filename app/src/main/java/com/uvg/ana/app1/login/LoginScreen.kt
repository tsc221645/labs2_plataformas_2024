package com.uvg.ana.app1.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uvg.ana.app1.R

@Composable
fun LoginScreen(onLoginSuccess: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo_rick_morty),
            contentDescription = "Rick and Morty Logo"
        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                // Esta acción debe disparar la navegación al listado de personajes
                onLoginSuccess()
            },
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(text = "Entrar", color = Color.White)
        }
        Spacer(modifier = Modifier.height(50.dp))
        Text(
            text = "Ana Laura Tschen - 221645",
            fontSize = 16.sp
        )
    }
}
