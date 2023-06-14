package com.example.locato

import android.os.Bundle
import androidx.compose.material3.ElevatedCard
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.locato.ui.theme.LocatoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LocatoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TLayout()
                }
            }
        }
    }
}
@Composable
fun TLayout() {
    var result by remember {
        mutableStateOf(1)
    }
    val imageResource = when(result) {
        1 -> R.drawable.screenshot_2023_06_10_at_7_42_33_am
        2 -> R.drawable.screenshot_2023_06_10_at_7_43_17_am
        3 -> R.drawable.screenshot_2023_06_10_at_7_44_13_am
        else -> 1
    }
    when(result) {
        1-> ArtBlocks(image = imageResource, content = "Seoul",
            onClickAction = {result=3}, onClickAction1 = {result=2})

        2-> {
            ArtBlocks(image = imageResource, content = "Kyoto",
                onClickAction = {result=1}) { result = 3 }
        }

        3-> ArtBlocks(image = imageResource, content = "HongKong",
            onClickAction = {result=2}) { result = 1 }
    }
}
@Composable
fun ArtBlocks(
    image: Int,
    content: String,
    onClickAction: () -> Unit,
    onClickAction1: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
                .fillMaxSize()
                .verticalScroll(
                    rememberScrollState()
                )
        ) {
            ElevatedCard(modifier = Modifier, shape = ShapeDefaults.ExtraLarge,
                colors = CardDefaults.cardColors(containerColor = Color.Red),
                elevation = CardDefaults.cardElevation(defaultElevation = 20.dp)
            ) {
                Image(painter = painterResource(id = image), contentDescription = content)
            }
            Spacer(modifier = Modifier.padding(50.dp))
            ElevatedCard(modifier=Modifier.background(color = Color.Transparent),shape = ShapeDefaults.ExtraSmall) {
                Text(text = content, modifier = Modifier.background(color= Color.Transparent))
            }
        }
        Row(
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(align = Alignment.BottomCenter)
                .padding(20.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Button(onClick = onClickAction) { //pls don't add {} this shit in the onclick assignment
                Text(text = "Previous")
            }
            Spacer(modifier = Modifier.padding(16.dp))
            Button(onClick = onClickAction1) {
                Text(text = "Next")
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LocatoTheme {
        TLayout()
    }
}