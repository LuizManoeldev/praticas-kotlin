package com.example.pratica_estado

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.res.painterResource


@Composable
fun ArtGallery(artList: List<ArtPiece>) {
    var currentIndex by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Exibir a imagem atual
        Image(
            painter = painterResource(id = artList[currentIndex].imageRes),
            contentDescription = null,
            modifier = Modifier.size(300.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Exibir título e autor
        Text(text = artList[currentIndex].title, fontSize = 18.sp, fontWeight = FontWeight.Bold)
        Text(text = artList[currentIndex].artist, fontSize = 14.sp, fontStyle = FontStyle.Italic)

        Spacer(modifier = Modifier.height(16.dp))

        // Botões de navegação
        Row {
            Button(onClick = {
                if (currentIndex > 0) currentIndex--
            }) {
                Text("Previous")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = {
                if (currentIndex < artList.size - 1) currentIndex++
            }) {
                Text("Next")
            }
        }
    }
}

// Estrutura de dados para armazenar as informações da arte
data class ArtPiece(val imageRes: Int, val title: String, val artist: String)
