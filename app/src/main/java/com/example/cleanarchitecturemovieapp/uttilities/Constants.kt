package com.example.cleanarchitecturemovieapp.uttilities

import com.example.cleanarchitecturemovieapp.BuildConfig

object Constants {
    const val BASE_URL = "https://api.themoviedb.org/3/"
    const val BASE_URL_IMAGE = "https://image.tmdb.org/t/p/original"
    const val API_KEY = BuildConfig.API_KEY

    fun getImageUrl(path: String): String {
        return BASE_URL_IMAGE + path
    }

    const val DB_NAME = "Movie.db"
    const val DB_VERSION = 2
}