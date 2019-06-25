package com.chaitanya.easyrelocate.ui.post

import androidx.lifecycle.MutableLiveData
import com.chaitanya.easyrelocate.base.BaseViewModel
import com.chaitanya.easyrelocate.model.Deliveries

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