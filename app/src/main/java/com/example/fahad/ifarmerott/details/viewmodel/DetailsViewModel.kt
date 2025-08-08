package com.example.fahad.ifarmerott.details.viewmodel

import androidx.lifecycle.ViewModel
import com.example.fahad.ifarmerott.common.data.model.Movie
import com.example.fahad.ifarmerott.common.repository.MovieRepository

class DetailsViewModel(
    private val repository: MovieRepository
): ViewModel() {

    suspend fun getMovieDetails(imdbId: String): Movie? {
        //todo: need to update repository api
        return repository.getMovieDetails(imdbId)
    }
}