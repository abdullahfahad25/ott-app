package com.example.fahad.ifarmerott.home.viewmodel

import androidx.lifecycle.ViewModel
import com.example.fahad.ifarmerott.common.data.model.MovieResponse
import com.example.fahad.ifarmerott.common.repository.MovieRepository

class HomeViewModel(
    private val repository: MovieRepository
): ViewModel() {
    suspend fun loadBatmanMovies(): MovieResponse {
        return repository.searchMovies("Batman", 1)
    }

    suspend fun loadLatestMovies(): MovieResponse {
        return repository.searchMovies("movie", 1, "2022")
    }

    suspend fun loadCarouselMovies(): MovieResponse {
        return repository.searchMovies("Marvel", 1)
    }
}