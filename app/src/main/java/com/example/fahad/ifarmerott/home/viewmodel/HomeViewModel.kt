package com.example.fahad.ifarmerott.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fahad.ifarmerott.common.data.model.MovieResponse
import com.example.fahad.ifarmerott.common.repository.MovieRepository
import com.example.fahad.ifarmerott.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(
    private val repository: MovieRepository
): ViewModel() {
//    suspend fun loadBatmanMovies(): MovieResponse {
//        return repository.searchMovies(Constants.BATMAN_MOVIE_VALUE, 1)
//    }
//
//    suspend fun loadLatestMovies(): MovieResponse {
//        return repository.searchMovies(Constants.LATEST_MOVIE_VALUE, 1, Constants.LATEST_MOVIE_YEAR)
//    }
//
//    suspend fun loadCarouselMovies(): MovieResponse {
//        return repository.searchMovies(Constants.CAROUSEL_MOVIE_VALUE, 1)
//    }

    private val _carouselMovies = MutableLiveData<MovieResponse>()
    val carouselMovies: LiveData<MovieResponse> get() = _carouselMovies

    private val _batmanMovies = MutableLiveData<MovieResponse>()
    val batmanMovies: LiveData<MovieResponse> get() = _batmanMovies

    private val _latestMovies = MutableLiveData<MovieResponse>()
    val latestMovies: LiveData<MovieResponse> get() = _latestMovies

    fun loadCarouselMovies() {
        viewModelScope.launch {
            val responsee = withContext(Dispatchers.IO) {
                repository.searchMovies(Constants.CAROUSEL_MOVIE_VALUE, 1)
            }
            _carouselMovies.value = responsee
        }
    }

    fun loadBatmanMovies() {
        viewModelScope.launch {
            val response = withContext(Dispatchers.IO) {
                repository.searchMovies(Constants.BATMAN_MOVIE_VALUE, 1)
            }
            _batmanMovies.value = response
        }
    }

    fun loadLatestMovies() {
        viewModelScope.launch {
            val response = withContext(Dispatchers.IO) {
                repository.searchMovies(Constants.LATEST_MOVIE_VALUE, 1, Constants.LATEST_MOVIE_YEAR)
            }
            _latestMovies.value = response
        }
    }
}