package com.example.android.movies.views.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.android.movies.R
import com.example.android.movies.models.ProductionCompany
import com.example.android.movies.utils.Constants
import com.example.android.movies.utils.Methods
import kotlinx.android.synthetic.main.item_company.view.*

class ProducersAdapter(var producerList: List<ProductionCompany>):
    RecyclerView.Adapter<ProducersAdapter.ProducerViewHolder>() {

    class ProducerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val producerImage: ImageView = itemView.iv_producer_image

        fun bind(producer: ProductionCompany){
            Methods.imageUrl(itemView.context, Constants.IMAGES + producer.logo_path, producerImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProducerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_company, parent, false)
        return ProducerViewHolder(view)
    }

    override fun getItemCount(): Int = producerList.size

    override fun onBindViewHolder(holder: ProducerViewHolder, position: Int) {
        holder.bind(producerList[position])
    }

    fun updateList(list: List<ProductionCompany>){
        producerList = list
        notifyDataSetChanged()
    }
}