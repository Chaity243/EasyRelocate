package com.chaitanya.easyrelocate.network

import com.chaitanya.easyrelocate.model.Deliveries
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * The interface which provides methods to get result of webservices
 */
interface DeliveryAPI {
    /**
     * Get the list of the deliveries from the API
     */
    @GET("/deliveries")
    fun getDeliveries(): Observable<List<Deliveries>>
}