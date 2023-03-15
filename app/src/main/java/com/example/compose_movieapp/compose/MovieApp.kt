package com.example.compose_movieapp.compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun MovieApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(navController)
        }
        composable(
            "detail/{number}",
            arguments = listOf(navArgument("number") { type = NavType.IntType })
        ) {
            DetailScreen(
                navController = navController,
                number = it.arguments?.getInt("number") ?: 0
            )
        }
    }
}

@Preview
@Composable
fun MovieAppPreview(){
    MovieApp()
}