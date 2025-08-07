package com.example.fahad.ifarmerott.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fahad.ifarmerott.R
import com.example.fahad.ifarmerott.repository.MovieRepository
import com.example.fahad.ifarmerott.ui.listing.ListingActivity
import com.example.fahad.ifarmerott.ui.listing.MovieAdapter
import com.example.fahad.ifarmerott.viewmodel.HomeViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeActivity : AppCompatActivity() {
    private val TAG = "HomeActivity"

    private lateinit var batmanRecyclerView: RecyclerView
    private lateinit var latestRecyclerView: RecyclerView
    private lateinit var batmanAdapter: MovieAdapter
    private lateinit var latestAdapter: MovieAdapter

    private val viewModel = HomeViewModel(MovieRepository())

    private val job = Job()
    private val scope = CoroutineScope(Dispatchers.Main + job)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)

        batmanRecyclerView = findViewById(R.id.batmanRecyclerView)
        latestRecyclerView = findViewById(R.id.latestRecyclerView)

        batmanAdapter = MovieAdapter()
        latestAdapter = MovieAdapter()

        batmanRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        latestRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        batmanRecyclerView.adapter = batmanAdapter
        latestRecyclerView.adapter = latestAdapter

        loadBatmanMovies()
        loadLatestMovies()
    }

    private fun loadBatmanMovies() {
        scope.launch {
            try {
                val response = viewModel.loadBatmanMovies()
                Log.d(TAG, "loadBatmanMovies: $response")

                if (response.Response == "True") {
                    batmanAdapter.submitList(response.Search)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun loadLatestMovies() {
        scope.launch {
            try {
                val response = viewModel.loadLatestMovies()
                Log.d(TAG, "loadLatestMovies: $response")

                if (response.Response == "True") {
                    latestAdapter.submitList(response.Search)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }
}