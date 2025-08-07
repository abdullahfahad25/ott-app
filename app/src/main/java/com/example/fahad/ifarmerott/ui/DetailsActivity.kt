package com.example.fahad.ifarmerott.ui

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.example.fahad.ifarmerott.R
import com.example.fahad.ifarmerott.data.model.Movie
import com.example.fahad.ifarmerott.repository.MovieRepository
import com.example.fahad.ifarmerott.viewmodel.DetailsViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class DetailsActivity : AppCompatActivity() {
    private val TAG = "DetailsActivity"
    private val IMDB_ID = "imdbID"
    private val DEFAULT_ID = "tt1285016"

    private lateinit var player: ExoPlayer
    private lateinit var titleTextView: TextView
    private lateinit var descriptionTextView: TextView
    private lateinit var imdbId: String

    private val viewModel = DetailsViewModel(MovieRepository())

    private val job = Job()
    private val scope = CoroutineScope(Dispatchers.Main + job)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_details)

        titleTextView = findViewById(R.id.movieTitle)
        descriptionTextView = findViewById(R.id.movieDescription)

        imdbId = intent.getStringExtra(IMDB_ID) ?: DEFAULT_ID

        setupPlayer()
        loadMovieDetails(imdbId)
    }

    private fun setupPlayer() {
        player = ExoPlayer.Builder(this).build()
        val playerView = findViewById<PlayerView>(R.id.playerView)
        playerView.player = player
    }

    private fun loadMovieDetails(imdbId: String) {
        scope.launch {
            try {
                val response = viewModel.getMovieDetails(imdbId)
                Log.d(TAG, "loadMovieDetails: $response")
                if (response?.Response == "True") {
                    setupViews(response)
                }
            } catch (e: Exception) {
                Toast.makeText(this@DetailsActivity, "Failed to load movie details", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupViews(movie: Movie) {
        titleTextView.text = movie.Title
        descriptionTextView.text =  movie.Plot
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }
}