package com.example.fahad.ifarmerott.listing.viemodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fahad.ifarmerott.common.data.model.MovieResponse
import com.example.fahad.ifarmerott.common.repository.MovieRepository
import com.example.fahad.ifarmerott.utils.Constants.PAGE_ITEM_LIMIT
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ListingViewModel(
    private val repository: MovieRepository
): ViewModel() {
    private val TAG = "ListingViewModel"

    private val _moviesResponse = MutableLiveData<MovieResponse>()
    val moviesResponse: LiveData<MovieResponse> get() = _moviesResponse

    private val _errorResponse = MutableLiveData<String>()
    val errorResponse: LiveData<String> get() = _errorResponse

    var currentPage = 1

    private var isLastPage = false
    private var isLoading = false

    fun searchMovies(query: String, page: Int? = 1, year: String? = null) {
        if (isLoading || isLastPage) return

        isLoading = true
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    repository.searchMovies(query, currentPage, year)
                }
                if (response.Response == "True") {
                    _moviesResponse.value = response

                    if (response.Search.size < PAGE_ITEM_LIMIT) {
                        isLastPage = true
                    } else {
                        currentPage++
                    }
                } else {
                    _errorResponse.value = "No more results"
                    isLastPage = true
                }
            } catch (e: Exception) {
                _errorResponse.value = "No more results"
            } finally {
                isLoading = false
            }
        }
    }
}