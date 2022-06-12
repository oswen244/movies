package com.example.android.movies.app.views.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.movies.databinding.ItemMovieBinding
import com.example.android.movies.utils.Constants
import com.example.android.movies.utils.Methods
import com.movies.core.domain.entity.MovieEntity

@SuppressLint("NotifyDataSetChanged")
class MovieListAdapter(var movieList: ArrayList<MovieEntity>, private val listener: (MovieEntity) -> Unit): RecyclerView.Adapter<MovieListAdapter.MovieListViewHolder>() {

    class MovieListViewHolder(itemMovie: ItemMovieBinding): RecyclerView.ViewHolder(itemMovie.root){
        private val movieImage: ImageView = itemMovie.ivMovieImage
        private val movieTitle: TextView = itemMovie.tvMovieTitle
        private val movieRate: TextView = itemMovie.tvMovieRate

        fun bind(movie: MovieEntity, listener: (MovieEntity) -> Unit) = with(itemView){
            Methods.imageUrl(itemView.context, Constants.IMAGES+movie.backdrop_path, movieImage)
            movieTitle.text = movie.title
            movieRate.text = movie.vote_average.toString()
            setOnClickListener {
                listener(movie)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder {
        val view = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieListViewHolder(view)
    }

    override fun getItemCount(): Int = movieList.size

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        holder.bind(movieList[position], listener)
    }

    fun update(newList: ArrayList<MovieEntity>){
        movieList = newList
        notifyDataSetChanged()
    }

    fun addToList(movies: List<MovieEntity>) {
        val prevCount = itemCount
        this.movieList.addAll(movies)
        if (prevCount > movies.size) {
            notifyDataSetChanged()
        } else {
            notifyItemRangeInserted(prevCount, movies.size)
        }
    }

    fun clearList(){
        this.movieList.clear()
        notifyDataSetChanged()
    }
}