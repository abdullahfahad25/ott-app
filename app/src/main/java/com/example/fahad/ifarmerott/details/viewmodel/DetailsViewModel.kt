package com.example.fahad.ifarmerott.details.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fahad.ifarmerott.common.data.model.Movie
import com.example.fahad.ifarmerott.common.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailsViewModel(
    private val repository: MovieRepository
): ViewModel() {

    private val _movieDetails = MutableLiveData<Movie?>()
    val movieDetails: LiveData<Movie?> get() = _movieDetails

    fun loadMovieDetails(imdbId: String) {
        viewModelScope.launch {
            val movie = withContext(Dispatchers.IO) {
                repository.getMovieDetails(imdbId)
            }
            _movieDetails.value = movie
        }
    }
}