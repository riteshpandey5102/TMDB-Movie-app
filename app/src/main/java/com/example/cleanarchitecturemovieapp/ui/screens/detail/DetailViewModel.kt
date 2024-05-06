package com.example.cleanarchitecturemovieapp.ui.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanarchitecturemovieapp.data.Resource
import com.example.cleanarchitecturemovieapp.data.model.Movie
import com.example.cleanarchitecturemovieapp.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val movieRepository: MovieRepository) :
    ViewModel() {

    private val _detailMovie = MutableStateFlow<Resource<Movie>>(Resource.Loading())
    val detailMovie: StateFlow<Resource<Movie>> get() = _detailMovie

    fun getFavoriteMovies(movieId: Int) = viewModelScope.launch {
        movieRepository.getMovieById(movieId).collect {
            _detailMovie.value = it
        }
    }

    fun toggleFavorite(movieId: Int, isFavorite: Boolean) = viewModelScope.launch {
        movieRepository.updateMovieById(movieId, isFavorite)
    }
}