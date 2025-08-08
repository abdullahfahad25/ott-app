package com.example.fahad.ifarmerott.listing.viemodel

import com.example.fahad.ifarmerott.common.repository.MovieRepository
import org.junit.Assert.*

import org.junit.Test

class ListingViewModelTest {
    private val repository = MovieRepository()
    private val viewModel = ListingViewModel(repository)

    @Test
    fun moviesResponseTest() {
        assertEquals(null, viewModel.moviesResponse.value)
    }

    @Test
    fun errorResponse() {
        assertEquals(null, viewModel.errorResponse.value)
    }

    @Test
    fun getCurrentPageTest() {
        assertEquals(1, viewModel.currentPage)
    }

    @Test
    fun setCurrentPageTest() {
        viewModel.currentPage = 3
        assertEquals(3, viewModel.currentPage)
    }
}