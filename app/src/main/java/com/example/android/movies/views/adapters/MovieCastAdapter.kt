package com.example.android.movies.views.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.movies.R
import com.example.android.movies.models.MovieCast
import com.example.android.movies.utils.Constants
import com.example.android.movies.utils.Methods
import kotlinx.android.synthetic.main.item_cast.view.*

class MovieCastAdapter(private var castList: List<MovieCast>):
    RecyclerView.Adapter<MovieCastAdapter.CastViewHolder>() {

    class CastViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val actorPicture: ImageView = itemView.iv_actor_picture
        private val actorName: TextView = itemView.tv_actor_name
        private val character: TextView = itemView.tv_character

        fun bind(cast: MovieCast){
            Methods.imageUrl(itemView.context, Constants.IMAGES + cast.profile_path, actorPicture)
            actorName.text = cast.name
            character.text = cast.character
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cast, parent, false)
        return CastViewHolder(view)
    }

    override fun getItemCount(): Int = castList.size

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        holder.bind(castList[position])
    }

    fun updateList(list: List<MovieCast>){
        castList = list
        notifyDataSetChanged()
    }
}