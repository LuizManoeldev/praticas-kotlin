package com.example.pratica_estado

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
                val artList = listOf(
                    ArtPiece(R.drawable.art2, "Sailing Under the Bridge", "Kat Kuan (2017)"),
                    ArtPiece(R.drawable.art1, "Starry Night", "Vincent van Gogh (1889)"),
                    ArtPiece(R.drawable.art3, "The Persistence of Memory", "Salvador Dali (1931)")
                )
                ArtGallery(artList)

        }
    }
}
