package com.example.fahad.ifarmerott.viewmodel

import androidx.lifecycle.ViewModel
import com.example.fahad.ifarmerott.data.model.MovieResponse
import com.example.fahad.ifarmerott.repository.MovieRepository

class DetailsViewModel(
    private val repository: MovieRepository
): ViewModel() {

    suspend fun getMovieDetails(imdbId: String): MovieResponse {
        //todo: need to update repository api
        return repository.searchMovies("")
    }
}