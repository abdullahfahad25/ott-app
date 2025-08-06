package com.example.fahad.ifarmerott.repository

import com.example.fahad.ifarmerott.data.api.RetrofitInstance
import com.example.fahad.ifarmerott.data.model.MovieResponse

class MovieRepository {
    suspend fun searchMovies(query: String, page: Int = 1, year: String? = null): MovieResponse {
        //todo: Replace dummy api key with real one
        return RetrofitInstance.api.searchMovies("", query, page, year)
    }
}