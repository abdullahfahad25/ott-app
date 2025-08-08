package com.example.fahad.ifarmerott.common.repository

import com.example.fahad.ifarmerott.common.data.api.RetrofitInstance
import com.example.fahad.ifarmerott.common.data.model.Movie
import com.example.fahad.ifarmerott.common.data.model.MovieResponse
import com.example.fahad.ifarmerott.utils.Constants

class MovieRepository {
    suspend fun searchMovies(query: String, page: Int = 1, year: String? = null): MovieResponse {
        return RetrofitInstance.api.searchMovies(Constants.API_KEY, query, page, year)
    }

    suspend fun getMovieDetails(imdbId: String): Movie? {
        return RetrofitInstance.api.getMovieDetails(Constants.API_KEY, imdbId)
    }
}