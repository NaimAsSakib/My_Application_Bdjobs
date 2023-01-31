package com.example.myapplicationbdjobs.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplicationbdjobs.api.models.AppTable

@Database(entities = [AppTable:: class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
abstract fun getAppDAo(): AppDao
}