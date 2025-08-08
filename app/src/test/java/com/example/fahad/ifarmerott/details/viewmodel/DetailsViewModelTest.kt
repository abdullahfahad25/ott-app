package com.example.fahad.ifarmerott.details.viewmodel

import com.example.fahad.ifarmerott.common.data.model.Movie
import com.example.fahad.ifarmerott.common.repository.MovieRepository
import org.junit.Assert.*
import org.junit.Test

class DetailsViewModelTest {
    private val repository = MovieRepository()
    private val viewModel = DetailsViewModel(repository)

    @Test
    fun movieDetailsTest() {
        assertEquals(null, viewModel.movieDetails.value)
    }

    @Test
    fun movieDetailsNullTest() {
        assertNull(viewModel.movieDetails.value)
    }
}