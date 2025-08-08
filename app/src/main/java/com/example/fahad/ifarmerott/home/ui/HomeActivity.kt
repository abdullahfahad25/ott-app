package com.example.fahad.ifarmerott.home.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.fahad.ifarmerott.R
import com.example.fahad.ifarmerott.common.repository.MovieRepository
import com.example.fahad.ifarmerott.home.viewmodel.HomeViewModel
import com.example.fahad.ifarmerott.listing.ui.ListingActivity
import com.example.fahad.ifarmerott.common.component.MovieAdapter
import com.example.fahad.ifarmerott.utils.Constants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class HomeActivity : AppCompatActivity() {
    private val TAG = "HomeActivity"

    private lateinit var batmanRecyclerView: RecyclerView
    private lateinit var latestRecyclerView: RecyclerView
    private lateinit var carouselViewPager: ViewPager2
    private lateinit var batmanAdapter: MovieAdapter
    private lateinit var latestAdapter: MovieAdapter
    private lateinit var carouselAdapter: CarouselAdapter

    private lateinit var seeAllLatest: TextView
    private lateinit var seeAllBatman: TextView

    private val viewModel = HomeViewModel(MovieRepository())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d(TAG, "onCreate")
        setContentView(R.layout.activity_home)

        carouselViewPager = findViewById(R.id.carouselViewPager)
        batmanRecyclerView = findViewById(R.id.batmanRecyclerView)
        latestRecyclerView = findViewById(R.id.latestRecyclerView)
        seeAllLatest = findViewById(R.id.seeAllLatest)
        seeAllBatman = findViewById(R.id.seeAllBatman)

        carouselAdapter = CarouselAdapter()
        batmanAdapter = MovieAdapter()
        latestAdapter = MovieAdapter()

        batmanRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        latestRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        carouselViewPager.adapter = carouselAdapter
        batmanRecyclerView.adapter = batmanAdapter
        latestRecyclerView.adapter = latestAdapter

        setupClickListeners()
        setupObservers()

        loadCarouselMovies()
        loadBatmanMovies()
        loadLatestMovies()
    }

    private fun setupClickListeners() {
        seeAllLatest.setOnClickListener {
            val intent = Intent(this, ListingActivity::class.java).apply {
                //set the field s to filter to get latest movies
                putExtra(Constants.LISTING_FIELD_QUERY, Constants.LATEST_MOVIE_VALUE)
            }

            startActivity(intent)
        }

        seeAllBatman.setOnClickListener {
            val intent = Intent(this, ListingActivity::class.java).apply {
                //set the field s to filter to get Batman movies
                putExtra(Constants.LISTING_FIELD_QUERY, Constants.BATMAN_MOVIE_VALUE)
            }

            startActivity(intent)
        }
    }

    private fun setupObservers() {
        viewModel.carouselMovies.observe(this) { response ->
            Log.d(TAG, "setupObservers: Carousel response: $response")
            if (response.Response == "True") {
                carouselAdapter.submitList(response.Search.take(5))
            }
        }

        viewModel.batmanMovies.observe(this) { response ->
            Log.d(TAG, "setupObservers: Batman response: $response")
            if (response.Response == "True") {
                batmanAdapter.submitList(response.Search)
            }
        }

        viewModel.latestMovies.observe(this) { response ->
            Log.d(TAG, "setupObservers: Latest movie response: $response")
            if (response.Response == "True") {
                latestAdapter.submitList(response.Search)
            }
        }
    }

    //filter used for carousel movies is 'Marvel'
    private fun loadCarouselMovies() {
        viewModel.loadCarouselMovies()
    }

    private fun loadBatmanMovies() {
        viewModel.loadBatmanMovies()
    }

    private fun loadLatestMovies() {
        viewModel.loadLatestMovies()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
        viewModel.carouselMovies.removeObservers(this)
        viewModel.batmanMovies.removeObservers(this)
        viewModel.latestMovies.removeObservers(this)
    }
}