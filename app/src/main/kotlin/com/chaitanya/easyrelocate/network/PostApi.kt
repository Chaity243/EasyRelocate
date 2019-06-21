package com.chaitanya.easyrelocate.network

import io.reactivex.Observable
import com.chaitanya.easyrelocate.model.Post
import retrofit2.http.GET

/**
 * The interface which provides methods to get result of webservices
 */
interface PostApi {
    /**
     * Get the list of the pots from the API
     */
    @GET("/deliveries")
    fun getPosts(): Observable<List<Post>>
}