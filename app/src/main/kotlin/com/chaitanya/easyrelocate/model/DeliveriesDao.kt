package com.chaitanya.easyrelocate.model

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface DeliveriesDao {
    @get:Query("SELECT * FROM  Deliveries")
    val all: List<Deliveries>

    @Insert
    fun insertAll(vararg deliveries: Deliveries)
}