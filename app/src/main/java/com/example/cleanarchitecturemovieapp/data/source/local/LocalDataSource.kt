package com.example.cleanarchitecturemovieapp.data.source.local

import com.example.cleanarchitecturemovieapp.data.source.local.entity.MovieEntity
import com.example.cleanarchitecturemovieapp.data.source.local.room.MovieDAO
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(
    private val movieDao: MovieDAO
) {
    suspend fun insertMovies(movies: List<MovieEntity>) = movieDao.insertMovies(movies)

    fun getMovies(type: String) = movieDao.getMovies(type)

    fun getFavoriteMovies() = movieDao.getFavoriteMovies()

    fun getMovieById(movieId: Int) = movieDao.getMovieById(movieId)

    suspend fun updateMovieById(movieId: Int, isFavorite: Boolean) =
        movieDao.updateMovieById(movieId, isFavorite)
}