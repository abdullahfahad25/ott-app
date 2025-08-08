package com.example.fahad.ifarmerott.home.viewmodel

import com.example.fahad.ifarmerott.common.repository.MovieRepository
import org.junit.Assert.*

import org.junit.Test

class HomeViewModelTest {
    private val repository = MovieRepository()
    private val viewModel = HomeViewModel(repository)

    @Test
    fun carouselMoviesTest() {
        assertEquals(null, viewModel.carouselMovies.value)
    }

    @Test
    fun batmanMoviesTest() {
        assertEquals(null, viewModel.batmanMovies.value)
    }

    @Test
    fun latestMoviesTest() {
        assertEquals(null, viewModel.latestMovies.value)
    }
}