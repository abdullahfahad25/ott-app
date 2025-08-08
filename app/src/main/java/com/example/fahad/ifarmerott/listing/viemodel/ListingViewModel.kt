package com.example.fahad.ifarmerott.listing.viemodel

import androidx.lifecycle.ViewModel
import com.example.fahad.ifarmerott.common.data.model.MovieResponse
import com.example.fahad.ifarmerott.common.repository.MovieRepository

class ListingViewModel(
    private val repository: MovieRepository
): ViewModel() {

    suspend fun searchMovies(query: String, page: Int): MovieResponse {
        return repository.searchMovies(query, page)
    }
}