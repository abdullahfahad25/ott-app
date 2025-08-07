package com.example.fahad.ifarmerott.viewmodel

import androidx.lifecycle.ViewModel
import com.example.fahad.ifarmerott.data.model.MovieResponse
import com.example.fahad.ifarmerott.repository.MovieRepository

class HomeViewModel(
    private val repository: MovieRepository
): ViewModel() {
    suspend fun loadBatmanMovies(): MovieResponse {
        return repository.searchMovies("Batman", 1)
    }

    suspend fun loadLatestMovies(): MovieResponse {
        return repository.searchMovies("movie", 1, "2022")
    }
}