package com.example.compose_movieapp.compose

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.compose_movieapp.state.MovieState
import com.example.compose_movieapp.viewmodel.MainViewModel


@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavController, viewModel: MainViewModel) {
    Scaffold(
        topBar = { TopAppBar(title = { Text(text = "Main Screen") }) }
    ) {
        val state = viewModel.uiState.collectAsState()
        when (state.value) {
            is MovieState.Loading -> {
                Text(text = "Loading...", modifier = Modifier.padding(16.dp))
            }
            is MovieState.Success -> {
                val movieData = state.value as MovieState.Success
                if (movieData.data != null) {
                    LazyColumn {
                        items(movieData.data) { movie ->
                            MovieItem(
                                title = movie.movieNm,
                                rank = movie.rank,
                                openDate = movie.openDt,
                                modifier = Modifier.clickable {
                                    navController.navigate("detail/${movie.movieNm}")
                                }
                            )
                        }
                    }
                } else {
                    Text(text = "Loading Fail...", modifier = Modifier.padding(16.dp))
                }
            }
            else -> {}
        }
    }
}

@Composable
fun MovieItem(modifier: Modifier, title: String, rank: String, openDate: String) {
    Surface(modifier = modifier.fillMaxWidth()) {
        Column(modifier = modifier, verticalArrangement = Arrangement.Center) {
            Text(text = title, modifier = modifier.padding(9.dp))
            Row(modifier = modifier) {
                Text(text = rank, modifier = modifier.padding(start = 9.dp, bottom = 9.dp))
                Text(text = openDate, modifier = modifier.padding(start = 9.dp, bottom = 9.dp))
            }
        }
    }
}


@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(rememberNavController(), viewModel())
}

@Preview
@Composable
fun ItemPreview() {
    MovieItem(title = "1", rank = "1", openDate = "1", modifier = Modifier)
}