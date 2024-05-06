package com.example.cleanarchitecturemovieapp.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cleanarchitecturemovieapp.data.source.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<MovieEntity>)

    @Query("SELECT * FROM movieTable WHERE movieType = :movieType")
    fun getMovies(movieType: String): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movieTable WHERE id=:movieId ORDER BY id")
    fun getMovieById(movieId: Int): Flow<MovieEntity>

    @Query("UPDATE movieTable SET isFavorite = :isFavorite WHERE id = :movieId")
    suspend fun updateMovieById(movieId: Int, isFavorite: Boolean)

    @Query("SELECT * FROM movieTable WHERE isFavorite = 1")
    fun getFavoriteMovies(): Flow<List<MovieEntity>>
}