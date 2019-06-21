package com.chaitanya.easyrelocate.ui.post

import android.arch.lifecycle.MutableLiveData
import com.chaitanya.easyrelocate.base.BaseViewModel
import com.chaitanya.easyrelocate.model.Deliveries
import com.chaitanya.easyrelocate.model.Post

class DeliveriesViewModel: BaseViewModel() {
    private val deliveryId = MutableLiveData<Int>()
    private val deliveryDescription = MutableLiveData<String>()
    private val deliveryImg = MutableLiveData<String>()

    fun bind(delivery: Deliveries){
        deliveryId.value = delivery.id
        deliveryDescription.value = delivery.description
        deliveryImg.value = delivery.imageUrl
    }

    fun getDeliveryId():MutableLiveData<Int>{
        return deliveryId
    }

    fun getDeliveryDescription():MutableLiveData<String>{
        return deliveryDescription
    }
    fun getDeliveryImage():MutableLiveData<String>{
        return deliveryImg
    }
}