package com.example.fahad.ifarmerott.repository

import com.example.fahad.ifarmerott.data.api.RetrofitInstance
import com.example.fahad.ifarmerott.data.model.MovieResponse
import com.example.fahad.ifarmerott.utils.Constants

class MovieRepository {
    suspend fun searchMovies(query: String, page: Int = 1, year: String? = null): MovieResponse {
        return RetrofitInstance.api.searchMovies(Constants.API_KEY, query, page, year)
    }
}