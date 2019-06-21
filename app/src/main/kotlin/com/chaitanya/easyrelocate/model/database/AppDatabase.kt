package com.chaitanya.easyrelocate.model.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.chaitanya.easyrelocate.model.Post
import com.chaitanya.easyrelocate.model.PostDao

@Database(entities = [Post::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun postDao(): PostDao
}