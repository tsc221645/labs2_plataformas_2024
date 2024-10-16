package com.uvg.ana.app1.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uvg.ana.app1.R
import com.uvg.ana.app1.data.UserPreferences
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    userPreferences: UserPreferences,
    onLoginSuccess: () -> Unit
) {
    var userName by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()

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

        // Input para ingresar el nombre
        TextField(
            value = userName,
            onValueChange = { userName = it },
            label = { Text("Enter your name") }
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                if (userName.isNotBlank()) {
                    coroutineScope.launch {
                        userPreferences.saveUserName(userName)
                        onLoginSuccess()
                    }
                }
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
