package com.example.android.movies.views.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.android.movies.R
import com.example.android.movies.models.MovieList
import com.example.android.movies.utils.Constants
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieListAdapter(var movieList: List<MovieList>): RecyclerView.Adapter<MovieListAdapter.MovieListViewHolder>() {

    class MovieListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val movieImage: ImageView = itemView.iv_movie_image
        private val movieTitle: TextView = itemView.tv_movie_title
        private val movieRate: TextView = itemView.tv_movie_rate

        fun bind(movie:MovieList){
            Glide.with(itemView.context).load(Constants.IMAGES+movie.backdrop_path).into(movieImage)
            movieTitle.text = movie.title
            movieRate.text = movie.vote_average.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieListViewHolder(view)
    }

    override fun getItemCount(): Int = movieList.size

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        holder.bind(movieList[position])
    }

    fun update(newList: List<MovieList>){
        movieList = newList
        notifyDataSetChanged()
    }
}