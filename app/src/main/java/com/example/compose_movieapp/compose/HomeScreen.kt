package com.example.compose_movieapp.compose

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.compose_movieapp.state.MovieState
import com.example.compose_movieapp.viewmodel.MainViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavController, viewModel: MainViewModel) {
    Scaffold(
        topBar = { TopAppBar(title = { Text(text = "Main Screen") }) }
    ) {
        val date = rememberSaveable { mutableStateOf(LocalDate.now()) }
        val state = viewModel.uiState.collectAsState()
        Column(modifier = Modifier) {
            DateView(date = date, viewModel = viewModel)
            MovieList(state = state, navController = navController)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DateView(date: MutableState<LocalDate>, viewModel: MainViewModel) {
    Row(
        modifier = Modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = {
            date.value = date.value.plusDays(1)
            val today = date.value as LocalDate
            viewModel.uiState.value = MovieState.Loading
            viewModel.getMovies(today.format(DateTimeFormatter.BASIC_ISO_DATE).toString())
        }) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "null")
        }
        IconButton(onClick = {
            date.value = date.value.minusDays(1)
            val today = date.value as LocalDate
            viewModel.uiState.value = MovieState.Loading
            viewModel.getMovies(today.format(DateTimeFormatter.BASIC_ISO_DATE).toString())
        }) {
            Icon(imageVector = Icons.Default.Close, contentDescription = "null")
        }
        Spacer(modifier = Modifier.padding(start = 10.dp))
        Text(text = "${date.value.year} 년")
        Spacer(modifier = Modifier.padding(start = 10.dp))
        Text(text = "${date.value.month.value} 월")
        Spacer(modifier = Modifier.padding(start = 10.dp))
        Text(text = "${date.value.dayOfMonth} 일")
    }
}

@Composable
fun MovieList(state: State<MovieState>, navController: NavController) {
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
        is MovieState.Error -> {
            val movieData = state.value as MovieState.Error
            Text(text = "Fail... ${movieData.e.message}", modifier = Modifier.padding(16.dp))
        }
        else -> {}
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