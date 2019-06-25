package com.chaitanya.easyrelocate.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.chaitanya.easyrelocate.model.AppConverter.Converters
import com.chaitanya.easyrelocate.model.Deliveries
import com.chaitanya.easyrelocate.model.DeliveriesDao

@Database(entities = [Deliveries::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun deliveriesDao(): DeliveriesDao
}