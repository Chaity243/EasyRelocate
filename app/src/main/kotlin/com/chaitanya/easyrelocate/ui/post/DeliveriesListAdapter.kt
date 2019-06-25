package com.chaitanya.easyrelocate.ui.post

import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.chaitanya.easyrelocate.model.Deliveries
import com.chaity.easyrelocate.R
import com.chaity.easyrelocate.databinding.ItemDeliveryBinding


class DeliveriesListAdapter: RecyclerView.Adapter<DeliveriesListAdapter.ViewHolder>() {
    private lateinit var deliveryList:List<Deliveries>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemDeliveryBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_delivery, parent, false)
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

    class ViewHolder(private val binding: ItemDeliveryBinding): RecyclerView.ViewHolder(binding.root){
        private val viewModel = DeliveriesViewModel()

        fun bind(deliveries: Deliveries){
            viewModel.bind(deliveries)
            binding.viewModel = viewModel
        }
    }
}