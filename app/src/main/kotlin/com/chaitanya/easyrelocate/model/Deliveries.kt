package com.chaitanya.easyrelocate.model

import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * Class which provides a model for Deliveries
 * @constructor Sets all properties of the post
 * @property id the unique identifier of the deliveries
 * @property description the description details of the deliveries
 * @property location location of deliveries
 */
@Entity
data class Deliveries (
        @field:PrimaryKey
        val id: Int,
        val description: String,
        val imageUrl: String,
        val location: Location

)