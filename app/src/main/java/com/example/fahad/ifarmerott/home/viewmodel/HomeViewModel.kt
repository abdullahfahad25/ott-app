package com.example.fahad.ifarmerott.home.viewmodel

import androidx.lifecycle.ViewModel
import com.example.fahad.ifarmerott.common.data.model.MovieResponse
import com.example.fahad.ifarmerott.common.repository.MovieRepository
import com.example.fahad.ifarmerott.utils.Constants

class HomeViewModel(
    private val repository: MovieRepository
): ViewModel() {
    suspend fun loadBatmanMovies(): MovieResponse {
        return repository.searchMovies(Constants.BATMAN_MOVIE_VALUE, 1)
    }

    suspend fun loadLatestMovies(): MovieResponse {
        return repository.searchMovies(Constants.LATEST_MOVIE_VALUE, 1, Constants.LATEST_MOVIE_YEAR)
    }

    suspend fun loadCarouselMovies(): MovieResponse {
        return repository.searchMovies(Constants.CAROUSEL_MOVIE_VALUE, 1)
    }
}