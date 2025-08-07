package com.example.fahad.ifarmerott.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fahad.ifarmerott.R
import com.example.fahad.ifarmerott.data.model.Movie

class CarouselAdapter: RecyclerView.Adapter<CarouselAdapter.CarouselViewHolder>() {
    private val movies = mutableListOf<Movie>()

    fun submitList(list: List<Movie>) {
        movies.clear()
        movies.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_carousel, parent, false)
        return CarouselViewHolder(view)
    }

    override fun onBindViewHolder(holder: CarouselViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount(): Int = movies.size

    class CarouselViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val bannerImageView: ImageView = itemView.findViewById(R.id.bannerImageView)

        fun bind(movie: Movie) {
            Glide.with(itemView.context)
                .load(movie.Poster)
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(bannerImageView)
        }
    }
}