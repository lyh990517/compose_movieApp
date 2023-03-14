package com.example.compose_movieapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.example.compose_movieapp.ui.theme.Compose_movieAppTheme
import com.example.compose_movieapp.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

@AndroidEntryPoint
class MainActivity : ComponentActivity(), CoroutineScope {

    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getMovies("20230301")
        lifecycleScope.launch {
            viewModel.uiState.collect {
                when (it) {
                    is MovieState.Loading -> loading()
                    is MovieState.Success -> successLoad(it)
                    else -> {}
                }
            }
        }
        setContent {
            Compose_movieAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }

    private fun successLoad(it: MovieState.Success) {
        it.data?.forEach {
            Log.e("log", "$it")
        }
    }

    private fun loading() {
        Log.e("log", "loading")
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + Job()
}

@Composable
fun Greeting(
    name: String,
    viewModel: MainViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val a = viewModel.uiState.collectAsState()
    Text(text = "Hello ${a.value}!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Compose_movieAppTheme {
        Greeting("Android")
    }
}