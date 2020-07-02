package com.example.android.movies.views.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.movies.R
import com.example.android.movies.models.Genre
import kotlinx.android.synthetic.main.item_genre.view.*

class GenreAdapter(var genreList: List<Genre>): RecyclerView.Adapter<GenreAdapter.GenreViewHolder>() {

    class GenreViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val tvGenreName: TextView = view.tv_genre

        fun bind(genre: Genre?){
            tvGenreName.text = genre?.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_genre, parent, false)
        return GenreViewHolder(view)
    }

    override fun getItemCount(): Int = genreList.size

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        holder.bind(genreList.get(position))
    }

    fun updateList(newGenreList: List<Genre>){
        genreList = newGenreList
        notifyDataSetChanged()
    }
}