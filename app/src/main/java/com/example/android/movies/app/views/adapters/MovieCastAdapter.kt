package com.example.android.movies.app.views.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.movies.databinding.ItemCastBinding
import com.example.android.movies.utils.Constants
import com.example.android.movies.utils.Methods
import com.movies.core.domain.entity.MovieCastEntity

class MovieCastAdapter(private var castList: List<MovieCastEntity>?):
    RecyclerView.Adapter<MovieCastAdapter.CastViewHolder>() {

    class CastViewHolder(itemCast: ItemCastBinding): RecyclerView.ViewHolder(itemCast.root) {
        private val actorPicture: ImageView = itemCast.ivActorPicture
        private val actorName: TextView = itemCast.tvActorName
        private val character: TextView = itemCast.tvCharacter

        fun bind(cast: MovieCastEntity?){
            Methods.imageUrl(itemView.context, Constants.IMAGES + cast?.profile_path, actorPicture)
            actorName.text = cast?.name
            character.text = cast?.character
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {
        val view = ItemCastBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CastViewHolder(view)
    }

    override fun getItemCount(): Int {
        castList?.let {
            return it.size
        }?: return 0
    }

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        holder.bind(castList?.get(position))
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(list: List<MovieCastEntity>){
        castList = list
        notifyDataSetChanged()
    }
}