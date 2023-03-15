package com.example.compose_movieapp.compose

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController


@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        topBar = { TopAppBar(title = { Text(text = "Main Screen") }) }
    ) {
        val numbers = (1..4).toList()

        LazyColumn {
            items(numbers) {number ->
                ListItem(
                    text = { Text(text = "Number $number") },
                    modifier = Modifier.clickable {
                        navController.navigate("detail/$number")
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(rememberNavController())
}