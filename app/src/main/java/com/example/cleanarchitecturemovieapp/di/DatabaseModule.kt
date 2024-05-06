package com.example.cleanarchitecturemovieapp.di

import android.content.Context
import androidx.room.Room
import com.example.cleanarchitecturemovieapp.data.source.local.room.MovieDAO
import com.example.cleanarchitecturemovieapp.data.source.local.room.MovieDatabase
import com.example.cleanarchitecturemovieapp.uttilities.Constants.DB_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): MovieDatabase {
        return Room.databaseBuilder(
            context = context,
            klass = MovieDatabase::class.java,
            name = DB_NAME
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideMovieDao(database: MovieDatabase): MovieDAO = database.movieDao()
}