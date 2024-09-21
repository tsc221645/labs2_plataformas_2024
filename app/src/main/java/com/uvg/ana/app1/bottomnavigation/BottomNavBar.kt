package com.uvg.ana.app1.bottomnavigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationSearching
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.material3.*
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun BottomNavBar(selectedItem: Int, onItemSelected: (Int) -> Unit) {
    // Cambiamos el esquema de colores de la barra de navegación
    MaterialTheme(
        colorScheme = lightColorScheme(
            primary = Color(0xFF5A799D), // Color de fondo personalizado
            onPrimary = Color.White // Color del texto e íconos
        )
    ) {
        NavigationBar(
            containerColor = MaterialTheme.colorScheme.primary, // Color de fondo de la barra
            contentColor = MaterialTheme.colorScheme.onPrimary // Color del contenido (íconos y texto)
        ) {
            NavigationBarItem(
                icon = { Icon(Icons.Filled.People, contentDescription = "People") },
                label = { Text("Characters") },
                selected = selectedItem == 0,
                onClick = { onItemSelected(0) }
            )
            NavigationBarItem(
                icon = { Icon(Icons.Filled.LocationSearching, contentDescription = "Locations") },
                label = { Text("Locations") },
                selected = selectedItem == 1,
                onClick = { onItemSelected(1) }
            )
            NavigationBarItem(
                icon = { Icon(Icons.Filled.Person, contentDescription = "Person") },
                label = { Text("Profile") },
                selected = selectedItem == 2,
                onClick = { onItemSelected(2) }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewBottomNavBar() {
    BottomNavBar(selectedItem = 0, onItemSelected = {})
}
