package com.example.compose_movieapp.compose

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DetailScreen(navController: NavController, number: Int) {
    Scaffold(topBar = { TopAppBar(title = { Text(text = "Detail Screen") }) })
    {
        Text(
            text = "Number $number",
            style = MaterialTheme.typography.h2,
            modifier = Modifier.fillMaxSize(),
            color = Color.Black,
        )
        BackHandler {
            navController.popBackStack()
        }
    }
}

@Preview
@Composable
fun DetailPreview() {
    DetailScreen(navController = rememberNavController(), 1)
}