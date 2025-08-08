package com.example.fahad.ifarmerott.listing.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fahad.ifarmerott.R
import com.example.fahad.ifarmerott.common.component.MovieAdapter
import com.example.fahad.ifarmerott.common.repository.MovieRepository
import com.example.fahad.ifarmerott.listing.viemodel.ListingViewModel
import com.example.fahad.ifarmerott.utils.Constants

class ListingActivity : AppCompatActivity() {
    private val TAG = "ListingActivity"
    private val THRESHOLD = 5

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MovieAdapter
    private val viewModel = ListingViewModel(MovieRepository())

    //get filter value s to search from intent. Default is 'movie'
    private val query: String by lazy { intent.getStringExtra(Constants.LISTING_FIELD_QUERY)
        ?: Constants.LATEST_MOVIE_VALUE }
    private val year: String? by lazy { intent.getStringExtra(Constants.LISTING_FIELD_YEAR)
        ?: null}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d(TAG, "onCreate")
        setContentView(R.layout.activity_listing)

        recyclerView = findViewById(R.id.listingRecyclerView)
        adapter = MovieAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        setupObservers()
        setupScrollListener()
        loadNextPage()
    }

    private fun setupObservers() {
        viewModel.moviesResponse.observe(this) { response ->
            Log.d(TAG, "setupObservers: movieResponse: $response")
            if (response?.Search?.isNotEmpty() == true) {
                Log.d(TAG, "setupObservers: currentPage = ${viewModel.currentPage}")

                adapter.submitList(response.Search, viewModel.currentPage != 1)
            }
        }

        viewModel.errorResponse.observe(this) { message ->
            Log.d(TAG, "setupObservers: errorResponse: $message")
            if (!message.isNullOrEmpty()) {
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    //Setup scroll listener for pagination
    private fun setupScrollListener() {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(rv: RecyclerView, dx: Int, dy: Int) {
                if (dy <= 0) {
                    Log.d(TAG, "onScrolled: dy <= 0. return")
                    return
                }

                val layoutManager = rv.layoutManager as LinearLayoutManager
                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
                Log.d(TAG, "onScrolled: visibleItemCount = ${visibleItemCount}, " +
                        "totalItemCount = ${totalItemCount}, " +
                        "firstVisibleItemPosition = $firstVisibleItemPosition")

                if (visibleItemCount + firstVisibleItemPosition >= totalItemCount - THRESHOLD
                    && firstVisibleItemPosition >= 0) {
                    loadNextPage()
                }
            }
        })
    }

    //Load movie details
    private fun loadNextPage() {
        viewModel.searchMovies(query, null, year)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
        viewModel.moviesResponse.removeObservers(this)
        viewModel.errorResponse.removeObservers(this)
    }
}