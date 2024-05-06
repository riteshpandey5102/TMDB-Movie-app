package com.example.cleanarchitecturemovieapp.ui.screens.favorite

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
class FavoriteViewModel @Inject constructor(private val movieRepository: MovieRepository) :
    ViewModel() {
    private val _favoriteMovies = MutableStateFlow<Resource<List<Movie>>>(Resource.Loading())
    val favoriteMovies: StateFlow<Resource<List<Movie>>> get() = _favoriteMovies
    fun getFavoriteMovies() = viewModelScope.launch {
        movieRepository.getFavoriteMovies().collect {
            _favoriteMovies.value = it
        }
    }
}