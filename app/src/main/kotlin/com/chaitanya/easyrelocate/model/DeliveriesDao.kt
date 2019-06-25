package com.chaitanya.easyrelocate.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.paging.DataSource


@Dao
interface DeliveriesDao {
    @get:Query("SELECT * FROM  Deliveries")
    val all: DataSource.Factory<Int,Deliveries>


    @Insert
    fun insertAll(vararg deliveries: Deliveries)
}