package com.example.fahad.ifarmerott.details.ui

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Typeface
import android.net.Uri
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.style.StyleSpan
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.example.fahad.ifarmerott.R
import com.example.fahad.ifarmerott.common.data.model.Movie
import com.example.fahad.ifarmerott.common.repository.MovieRepository
import com.example.fahad.ifarmerott.details.viewmodel.DetailsViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class DetailsActivity : AppCompatActivity() {
    private val TAG = "DetailsActivity"
    private val IMDB_ID = "imdbID"
    private val DEFAULT_ID = "tt1285016"    //Default movie: The Social Network
    //Video to be played
    private val VIDEO_URL = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/TearsOfSteel.mp4"

    private lateinit var player: ExoPlayer
    private lateinit var titleTextView: TextView
    private lateinit var descriptionTextView: TextView
    //imdbID of the movie to fetch details
    private lateinit var imdbId: String

    private val viewModel = DetailsViewModel(MovieRepository())

    private val job = Job()
    private val scope = CoroutineScope(Dispatchers.Main + job)

    //Video position saved in SharedPreferences
    private lateinit var sharedPreferences: SharedPreferences
    //Current Video position. It is saved
    private var currentPosition: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d(TAG, "onCreate")
        setContentView(R.layout.activity_details)

        titleTextView = findViewById(R.id.movieTitle)
        descriptionTextView = findViewById(R.id.movieDescription)

        sharedPreferences = getSharedPreferences("player_prefs", Context.MODE_PRIVATE)

        //get imdbID from intent to fetch details about that particular movie
        imdbId = intent.getStringExtra(IMDB_ID) ?: DEFAULT_ID

        setupPlayer()
        loadMovieDetails(imdbId)
    }

    //Video Player initialization
    private fun setupPlayer() {
        player = ExoPlayer.Builder(this).build()
        val playerView = findViewById<PlayerView>(R.id.playerView)
        playerView.player = player
    }

    //load movie details
    private fun loadMovieDetails(imdbId: String) {
        scope.launch {
            try {
                val response = viewModel.getMovieDetails(imdbId)
                Log.d(TAG, "loadMovieDetails: $response")
                if (response?.Response == "True") {
                    setupViews(response)
                    displayVideo()
                }
            } catch (e: Exception) {
                Toast.makeText(this@DetailsActivity, "Failed to load movie details", Toast.LENGTH_SHORT).show()
            }
        }
    }

    //movie details
    private fun setupViews(movie: Movie) {
        titleTextView.text = movie.Title

        val info = SpannableStringBuilder()
        info.append("Description: ", StyleSpan(Typeface.BOLD), 0)
        info.append("${movie.Plot}\n")
        info.append("Year: ", StyleSpan(Typeface.BOLD), 0)
        info.append("${movie.Year}\n")
        info.append("Rated: ", StyleSpan(Typeface.BOLD), 0)
        info.append("${movie.Rated}\n")
        info.append("Released: ", StyleSpan(Typeface.BOLD), 0)
        info.append("${movie.Released}\n")
        info.append("Runtime: ", StyleSpan(Typeface.BOLD), 0)
        info.append("${movie.Runtime}\n")
        info.append("Genre: ", StyleSpan(Typeface.BOLD), 0)
        info.append("${movie.Genre}\n")
        info.append("Director: ", StyleSpan(Typeface.BOLD), 0)
        info.append("${movie.Director}\n")
        info.append("Writer: ", StyleSpan(Typeface.BOLD), 0)
        info.append("${movie.Writer}\n")
        info.append("Actors: ", StyleSpan(Typeface.BOLD), 0)
        info.append("${movie.Actors}\n")
        info.append("Language: ", StyleSpan(Typeface.BOLD), 0)
        info.append("${movie.Language}\n")
        info.append("Country: ", StyleSpan(Typeface.BOLD), 0)
        info.append("${movie.Country}\n")
        info.append("Awards: ", StyleSpan(Typeface.BOLD), 0)
        info.append("${movie.Awards}")

        descriptionTextView.text =  info
    }

    //play video from last saved position
    private fun displayVideo() {
        //Get last saved video position
        val savedPosition = sharedPreferences.getLong(imdbId, 0)
        player.setMediaItem(MediaItem.fromUri(Uri.parse(VIDEO_URL)))
        player.prepare()
        player.seekTo(savedPosition)    //Set last saved video position
        player.play()
    }

    override fun onPause() {
        super.onPause()
        currentPosition = player.currentPosition
        //save current video position
        sharedPreferences.edit().putLong(imdbId, currentPosition).apply()
        player.pause()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
        player.release()
        job.cancel()
    }
}