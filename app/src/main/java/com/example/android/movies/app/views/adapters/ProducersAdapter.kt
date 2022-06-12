package com.example.android.movies.app.views.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.movies.databinding.ItemCompanyBinding
import com.example.android.movies.utils.Constants
import com.example.android.movies.utils.Methods
import com.movies.core.domain.entity.ProductionCompanyEntity

class ProducersAdapter(private var producerList: List<ProductionCompanyEntity>?):
    RecyclerView.Adapter<ProducersAdapter.ProducerViewHolder>() {

    class ProducerViewHolder(itemCompany: ItemCompanyBinding): RecyclerView.ViewHolder(itemCompany.root) {
        private val producerImage: ImageView = itemCompany.ivProducerImage

        fun bind(producer: ProductionCompanyEntity?){
            Methods.imageUrl(itemView.context, Constants.IMAGES + producer?.logo_path, producerImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProducerViewHolder {
        val view = ItemCompanyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProducerViewHolder(view)
    }

    override fun getItemCount(): Int {
        producerList?.let {
            return it.size
        }?: return 0
    }

    override fun onBindViewHolder(holder: ProducerViewHolder, position: Int) {
        holder.bind(producerList?.get(position))
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(list: List<ProductionCompanyEntity>){
        producerList = list
        notifyDataSetChanged()
    }
}