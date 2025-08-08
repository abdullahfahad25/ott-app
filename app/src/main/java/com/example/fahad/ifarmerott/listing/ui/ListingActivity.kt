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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ListingActivity : AppCompatActivity() {
    private val TAG = "ListingActivity"
    private val PAGE_ITEM_LIMIT = 10

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MovieAdapter
    private val viewModel = ListingViewModel(MovieRepository())

    private val job = Job()
    private val scope = CoroutineScope(Dispatchers.Main + job)

    private var currentPage = 1
    private var isLoading = false
    private var isLastPage = false
    //get filter value s to search from intent. Default is '2022'
    private val query: String by lazy { intent.getStringExtra(Constants.LISTING_FIELD_QUERY)
        ?: Constants.LATEST_MOVIE_YEAR }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d(TAG, "onCreate")
        setContentView(R.layout.activity_listing)

        recyclerView = findViewById(R.id.listingRecyclerView)
        adapter = MovieAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        setupScrollListener()
        loadMovies(currentPage)
    }

    //Setup scroll listener for pagination
    private fun setupScrollListener() {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(rv: RecyclerView, dx: Int, dy: Int) {
                if (dy <= 0) 
                    return

                val layoutManager = rv.layoutManager as LinearLayoutManager
                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
                Log.d(TAG, "onScrolled: visibleItemCount = ${visibleItemCount}, " +
                        "totalItemCount = ${totalItemCount}, " +
                        "firstVisibleItemPosition = $firstVisibleItemPosition")

                if (!isLoading && !isLastPage) {
                    if (visibleItemCount + firstVisibleItemPosition >= totalItemCount - 5
                        && firstVisibleItemPosition >= 0) {
                        loadMovies(currentPage + 1)
                    }
                }
            }
        })
    }

    //Load movie details
    private fun loadMovies(page: Int) {
        isLoading = true
        scope.launch {
            try {
                //Default filter value is '2022'
                val response = viewModel.searchMovies(query, page)
                Log.d(TAG, "loadMovies: page = ${page}, Search size = ${response.Search.size}")
                
                if (response.Response == "True") {
                    if (page == 1) {
                        adapter.submitList(response.Search)
                    } else {
                        val currentList = adapter.movies.toMutableList()
                        currentList.addAll(response.Search)
                        adapter.submitList(currentList)
                    }
                    currentPage = page
                    // If less than 10 results (OMDB default page size), consider last page
                    if (response.Search.size < PAGE_ITEM_LIMIT) {
                        isLastPage = true
                    }
                } else {
                    Toast.makeText(this@ListingActivity, "No more results", Toast.LENGTH_SHORT).show()
                    isLastPage = true
                }
            } catch (e: Exception) {
                Toast.makeText(this@ListingActivity, "Error loading movies", Toast.LENGTH_SHORT).show()
            }

            isLoading = false
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
        job.cancel()
    }
}