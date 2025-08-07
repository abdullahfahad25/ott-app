package com.example.fahad.ifarmerott.ui.listing

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fahad.ifarmerott.R
import com.example.fahad.ifarmerott.repository.MovieRepository
import com.example.fahad.ifarmerott.viewmodel.ListingViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ListingActivity : AppCompatActivity() {
    private val FIELD_QUERY = "query"
    private val DEFAULT_QUERY = "2022"

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MovieAdapter
    private val viewModel = ListingViewModel(MovieRepository())

    private val job = Job()
    private val scope = CoroutineScope(Dispatchers.Main + job)

    private var currentPage = 1
    private val query: String by lazy { intent.getStringExtra(FIELD_QUERY) ?: DEFAULT_QUERY }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_listing)

        recyclerView = findViewById(R.id.listingRecyclerView)
        adapter = MovieAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        loadMovies(currentPage)
        //todo: setup scroll listener
    }

    private fun loadMovies(page: Int) {
        scope.launch {
            try {
                val response = viewModel.searchMovies(query, page)
                if (response.Response == "True") {
                    adapter.submitList(response.Search)
                    //todo: update for infinite loading
                } else {
                    Toast.makeText(this@ListingActivity, "No response", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Toast.makeText(this@ListingActivity, "Error loading movies", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }
}