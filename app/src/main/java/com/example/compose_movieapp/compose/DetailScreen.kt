package com.example.compose_movieapp.compose

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.compose_movieapp.state.DetailState
import com.example.compose_movieapp.viewmodel.MainViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DetailScreen(navController: NavController, name: String, viewModel: MainViewModel) {
    viewModel.getOneMovie(name)
    val state = viewModel.detailState.collectAsState()
    Scaffold(topBar = { TopAppBar(title = { Text(text = "Detail Screen") }) })
    {
        when (state.value) {
            is DetailState.Loading -> {
                Text(
                    text = "Loading..",
                    style = MaterialTheme.typography.h2,
                    modifier = Modifier.fillMaxSize(),
                    color = Color.Black,
                )
            }
            is DetailState.Success -> {
                val data = state.value as DetailState.Success
                val movie = data.data
                Text(
                    text = "${movie.movieNm}",
                    style = MaterialTheme.typography.h2,
                    modifier = Modifier.fillMaxSize(),
                    color = Color.Black,
                )
            }
            is DetailState.Error -> {
                val detailData = state.value as DetailState.Error
                Text(
                    text = "fail.. ${detailData.e.message}",
                    style = MaterialTheme.typography.h2,
                    modifier = Modifier.fillMaxSize(),
                    color = Color.Black,
                )
            }
        }
        BackHandler {
            navController.popBackStack()
        }
    }
}

@Preview
@Composable
fun DetailPreview() {
}