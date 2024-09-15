package com.example.pratica_compose


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.example.pratica_compose.ui.theme.PraticacomposeTheme
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment
import kotlin.io.encoding.Base64

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PraticacomposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //ComposeArticleApp()
                    ComposeTaskManagerApp()
                }

            }
        }
    }




    @Composable
    fun ComposeArticleApp(){
        ArticleCard(
            title = stringResource(R.string.title_jetpack_compose_tutorial),
            shortDescription = stringResource(R.string.compose_short_desc),
            longDescription = stringResource(R.string.compose_long_desc),
            imagePainter = painterResource(R.drawable.bg_compose_background)
        )
    }



    @Composable
    fun ArticleCard(title: String,
                    shortDescription: String,
                    longDescription: String,
                    imagePainter: Painter,
                    modifier: Modifier = Modifier){

        Column(modifier = modifier) {
            Image(painter = imagePainter, contentDescription = null)
            Text(
                text = title,
                modifier = Modifier.padding(16.dp),
                fontSize = 24.sp
            )
            Text(
                text = shortDescription,
                modifier = Modifier.padding(start = 16.dp, end = 16.dp),
                textAlign = TextAlign.Justify
            )
            Text(
                text = longDescription,
                modifier = Modifier.padding(16.dp),
                textAlign = TextAlign.Justify
            )
        }

    }

    @Preview(showBackground = true)
    @Composable
    fun ComposeArticlePreview(){
        PraticacomposeTheme {
            ComposeArticleApp()
        }
    }

    @Composable
    fun ComposeTaskManagerApp(){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val image = painterResource(R.drawable.ic_task_completed)
            Image (painter = image, contentDescription = null)
            Text(
                text = stringResource(R.string.tasl_status),
                modifier = Modifier.padding(top = 24.dp, bottom = 8.dp),
                fontWeight = FontWeight.Bold
            )
            Text(
                text = stringResource(R.string.task_greating),
                fontSize = 16.sp
            )
        }
    }


    @Preview
    @Composable
    fun ComposeTaskManagerPreview(){
        PraticacomposeTheme {
            ComposeTaskManagerApp()
        }
    }

    @Composable
    fun ComposeQuadranteApp() {
        Column(Modifier.fillMaxWidth()) {
            Row(Modifier.weight(1f)) {
                ComposableInfoCard(
                    title = stringResource(R.string.first_title),
                    content= stringResource(R.string.first_description),
                    backgroundColor = Color(0xFFEADDFF),
                    modifier = Modifier.weight(1f)
                )
                ComposableInfoCard(
                    title = stringResource(R.string.second_title),
                    content = stringResource(R.string.second_description),
                    backgroundColor = Color(0xFFD0BCFF),
                    modifier = Modifier.weight(1f)
                )
            }
            Row(Modifier.weight(1f)) {
                ComposableInfoCard(
                    title = stringResource(R.string.third_title),
                    content = stringResource(R.string.third_description),
                    backgroundColor = Color(0xFFB69DF8),
                    modifier = Modifier.weight(1f)
                )
                ComposableInfoCard(
                    title = stringResource(R.string.fourth_title),
                    content = stringResource(R.string.fourth_description),
                    backgroundColor = Color(0xFFF6EDFF),
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }

    @Composable
    fun ComposableInfoCard(
        title: String,
        content: String,
        backgroundColor: Color,
        modifier: Modifier = Modifier
    ){
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(backgroundColor)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
          Text(
              text = title,
              modifier = Modifier.padding(bottom = 16.dp)
          )
          Text(
              text = content,
              textAlign = TextAlign.Justify
          )

        }
    }

    @Preview
    @Composable
    fun ComposeQuadrantesPreview(){
        PraticacomposeTheme {
            ComposeQuadranteApp()
        }
    }




}
