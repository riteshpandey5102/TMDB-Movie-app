package com.example.cleanarchitecturemovieapp.ui.navigation

sealed class MovieScreen(val route: String) {
    data object HOME : MovieScreen("Home")
    data object DETAIL : MovieScreen("Detail")
    data object FAVORITE : MovieScreen("Favorite")
}