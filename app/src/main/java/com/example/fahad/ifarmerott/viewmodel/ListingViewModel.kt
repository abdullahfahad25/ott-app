package com.example.fahad.ifarmerott.viewmodel

import androidx.lifecycle.ViewModel
import com.example.fahad.ifarmerott.data.model.MovieResponse
import com.example.fahad.ifarmerott.repository.MovieRepository

class ListingViewModel(
    private val repository: MovieRepository
): ViewModel() {

    suspend fun searchMovies(query: String, page: Int): MovieResponse {
        return repository.searchMovies(query, page)
    }
}