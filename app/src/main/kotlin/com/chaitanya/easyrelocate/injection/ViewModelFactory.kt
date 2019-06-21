package com.chaitanya.easyrelocate.injection

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.persistence.room.Room
import android.support.v7.app.AppCompatActivity
import com.chaitanya.easyrelocate.model.database.AppDatabase
import com.chaitanya.easyrelocate.ui.post.DeliveriesListViewModel

class ViewModelFactory(private val activity: AppCompatActivity): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DeliveriesListViewModel::class.java)) {
            val db = Room.databaseBuilder(activity.applicationContext, AppDatabase::class.java, "deliveries").build()
            @Suppress("UNCHECKED_CAST")
            return DeliveriesListViewModel(db.deliveriesDao()) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }
}