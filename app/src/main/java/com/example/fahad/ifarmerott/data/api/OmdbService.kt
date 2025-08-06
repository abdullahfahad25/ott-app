package com.example.fahad.ifarmerott.data.api

import com.example.fahad.ifarmerott.data.model.Movie
import com.example.fahad.ifarmerott.data.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface OmdbService {
    @GET("/")
    suspend fun searchMovies(
        @Query("apikey") apiKey: String,
        @Query("s") query: String,
        @Query("page") page: Int = 1,
        @Query("y") year: String? = null
    ): MovieResponse

    @GET("/")
    suspend fun getMovieDetails(
        @Query("i") imdbId: String,
        @Query("apikey") apiKey: String
    ): Movie?
}