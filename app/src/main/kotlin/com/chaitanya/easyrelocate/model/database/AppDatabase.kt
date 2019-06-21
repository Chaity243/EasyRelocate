package com.chaitanya.easyrelocate.model.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.chaitanya.easyrelocate.model.AppConverter.Converters
import com.chaitanya.easyrelocate.model.Deliveries
import com.chaitanya.easyrelocate.model.DeliveriesDao
import com.chaitanya.easyrelocate.model.Post
import com.chaitanya.easyrelocate.model.PostDao

@Database(entities = [Post::class, Deliveries::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun postDao(): PostDao
    abstract fun deliveriesDao(): DeliveriesDao
}