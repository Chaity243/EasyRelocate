package com.chaitanya.easyrelocate.ui.post

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.chaitanya.easyrelocate.model.Deliveries
import com.chaitanya.easyrelocate.model.Post
import com.chaity.easyrelocate.R
import com.chaity.easyrelocate.databinding.ItemPostBinding

class DeliveriesListAdapter: RecyclerView.Adapter<DeliveriesListAdapter.ViewHolder>() {
    private lateinit var deliveryList:List<Deliveries>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemPostBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_post, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(deliveryList[position])
    }

    override fun getItemCount(): Int {
        return if(::deliveryList.isInitialized) deliveryList.size else 0
    }

    fun updatePostList(deliveryList:List<Deliveries>){
        this.deliveryList = deliveryList
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemPostBinding):RecyclerView.ViewHolder(binding.root){
        private val viewModel = DeliveriesViewModel()

        fun bind(deliveries: Deliveries){
            viewModel.bind(deliveries)
            binding.viewModel = viewModel
        }
    }
}