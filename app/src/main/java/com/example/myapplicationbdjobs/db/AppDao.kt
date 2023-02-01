package com.example.myapplicationbdjobs.db

import androidx.room.*
import com.example.myapplicationbdjobs.api.models.AppTable

@Dao
interface AppDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addBookmark(appTable: AppTable)

    @Query("SELECT * FROM appTable")
    suspend fun getAllScheduleApps():List<AppTable>

    @Query("SELECT EXISTS(SELECT * FROM appTable WHERE id = :id) " )
    suspend fun checkIfExit(id:Int): Boolean

    @Delete
    suspend fun deleteBookmark(appTable: AppTable)
}