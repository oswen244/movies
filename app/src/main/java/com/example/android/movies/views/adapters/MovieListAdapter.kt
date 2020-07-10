package com.example.android.movies.views.adapters

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.example.android.movies.R
import com.example.android.movies.models.MovieList
import com.example.android.movies.utils.Constants
import com.example.android.movies.utils.Methods
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieListAdapter(var movieList: ArrayList<MovieList>, private val listener: (MovieList) -> Unit): RecyclerView.Adapter<MovieListAdapter.MovieListViewHolder>() {

    class MovieListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val movieImage: ImageView = itemView.iv_movie_image
        private val movieTitle: TextView = itemView.tv_movie_title
        private val movieRate: TextView = itemView.tv_movie_rate
        //private val loader: RelativeLayout = itemView.rl_loading_image

        fun bind(movie:MovieList, listener: (MovieList) -> Unit) = with(itemView){
            Methods.imageUrl(itemView.context, Constants.IMAGES+movie.backdrop_path, movieImage)
            /*Glide.with(itemView.context).load(Constants.IMAGES+movie.backdrop_path).listener(object :
                RequestListener<Drawable> {
                override fun onLoadFailed(p0: GlideException?, p1: Any?, p2: com.bumptech.glide.request.target.Target<Drawable>?, p3: Boolean): Boolean {
                    movieImage.visibility = View.GONE
                    loader.visibility = View.VISIBLE
                    return false
                }
                override fun onResourceReady(p0: Drawable?, p1: Any?, p2: com.bumptech.glide.request.target.Target<Drawable>?, p3: DataSource?, p4: Boolean): Boolean {
                    loader.visibility = View.GONE
                    movieImage.visibility = View.VISIBLE
                    return false
                }
            }).into(movieImage)*/
            movieTitle.text = movie.title
            movieRate.text = movie.vote_average.toString()
            setOnClickListener {
                listener(movie)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieListViewHolder(view)
    }

    override fun getItemCount(): Int = movieList.size

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        holder.bind(movieList[position], listener)
    }

    fun update(newList: ArrayList<MovieList>){
        movieList = newList
        notifyDataSetChanged()
    }

    fun addToList(movies: List<MovieList>) {
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