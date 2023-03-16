package com.example.compose_movieapp.compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.compose_movieapp.viewmodel.MainViewModel

@Composable
fun MovieApp(viewModel: MainViewModel = viewModel()) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(navController, viewModel)
        }
        composable(
            "detail/{name}",
            arguments = listOf(navArgument("name") { type = NavType.StringType })
        ) {
            DetailScreen(
                navController = navController,
                name = it.arguments?.getString("name") ?: "noThing",
                viewModel = viewModel
            )
        }
    }
}

@Preview
@Composable
fun MovieAppPreview() {
    MovieApp(viewModel())
}