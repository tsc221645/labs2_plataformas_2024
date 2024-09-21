package com.uvg.ana.app1.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uvg.ana.app1.R

@Composable
fun ProfileScreen(onLogoutClick: () -> Unit) {
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

        // Profile details
        Text("Nombre:", fontWeight = FontWeight.Bold, fontSize = 18.sp)
        Text("Ana Laura Tschen", fontSize = 18.sp)

        Spacer(modifier = Modifier.height(8.dp))

        Text("Carné:", fontWeight = FontWeight.Bold, fontSize = 18.sp)
        Text("221645", fontSize = 18.sp)

        Spacer(modifier = Modifier.height(24.dp))

        // Logout Button
        Button(
            onClick = { onLogoutClick() },
            modifier = Modifier
                .fillMaxWidth(0.6f)
                .height(50.dp)
        ) {
            Text("Cerrar sesión", fontSize = 16.sp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewProfileScreen() {
    ProfileScreen(onLogoutClick = {})
}
