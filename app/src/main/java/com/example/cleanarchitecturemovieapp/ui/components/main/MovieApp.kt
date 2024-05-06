package com.example.cleanarchitecturemovieapp.ui.components.main

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.cleanarchitecturemovieapp.ui.navigation.MovieNavHost
import com.example.cleanarchitecturemovieapp.ui.navigation.MovieScreen

@Composable
fun MovieApp(
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = backStackEntry?.destination?.route?.let { route ->
        when (route) {
            MovieScreen.HOME.route -> MovieScreen.HOME
            "${MovieScreen.DETAIL.route}/{movieId}" -> MovieScreen.DETAIL
            MovieScreen.FAVORITE.route -> MovieScreen.FAVORITE
            else -> null
        }
    } ?: MovieScreen.HOME

    Scaffold(
        topBar = {
            MoovTopAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = {
                    navController.navigateUp()
                },
                onFavoriteClicked = {
                    navController.navigate(MovieScreen.FAVORITE.route)
                }
            )
        },
        content = { innerPadding ->
            MovieNavHost(navController = navController, innerPadding = innerPadding)
        }
    )
}