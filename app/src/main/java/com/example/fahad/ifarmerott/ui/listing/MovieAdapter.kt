package com.example.fahad.ifarmerott.ui.listing

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fahad.ifarmerott.R
import com.example.fahad.ifarmerott.data.model.Movie

class MovieAdapter: RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    val movies = mutableListOf<Movie>()

    fun submitList(list: List<Movie>) {
        movies.clear()
        movies.addAll(list)
        notifyDataSetChanged()      //todo: optimization
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie_vertical, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount(): Int = movies.size

    class MovieViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val posterImageView: ImageView = itemView.findViewById(R.id.moviePoster)
        private val titleTextView: TextView = itemView.findViewById(R.id.movieTitle)

        fun bind(movie: Movie) {
            titleTextView.text = movie.Title
            Glide.with(itemView.context)
                .load(movie.Poster)
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(posterImageView)
        }
    }
}