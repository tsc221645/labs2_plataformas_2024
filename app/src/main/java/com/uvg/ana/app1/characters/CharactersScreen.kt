package com.uvg.ana.app1.characters

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.uvg.ana.app1.data.CharacterDb

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CharactersScreen(onCharacterClick: (Int) -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Characters") })
        }
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            CharacterDb.getAllCharacters().forEach { character ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onCharacterClick(character.id) }
                        .padding(16.dp)
                ) {
                    Image(
                        painter = rememberImagePainter(character.imageUrl),
                        contentDescription = character.name,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(50.dp)
                            .clip(CircleShape)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text(text = character.name, style = MaterialTheme.typography.titleLarge)
                        Text(text = "${character.species} - ${character.status}")
                    }
                }
            }
        }
    }
}
