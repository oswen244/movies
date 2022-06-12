package com.example.android.movies.app.views.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.movies.databinding.ItemGenreBinding
import com.movies.core.domain.entity.GenreEntity

class GenreAdapter(var genreList: List<GenreEntity>, private val listener: (GenreEntity) -> Unit): RecyclerView.Adapter<GenreAdapter.GenreViewHolder>() {

    class GenreViewHolder(genreItem: ItemGenreBinding): RecyclerView.ViewHolder(genreItem.root){
        private val tvGenreName: TextView = genreItem.tvGenre

        fun bind(genre: GenreEntity, listener: (GenreEntity) -> Unit) = with(itemView){
            tvGenreName.text = genre.name
            setOnClickListener {
                listener(genre)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        val view = ItemGenreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GenreViewHolder(view)
    }

    override fun getItemCount(): Int = genreList.size

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        holder.bind(genreList[position], listener)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newGenreList: List<GenreEntity>){
        genreList = newGenreList
        notifyDataSetChanged()
    }
}