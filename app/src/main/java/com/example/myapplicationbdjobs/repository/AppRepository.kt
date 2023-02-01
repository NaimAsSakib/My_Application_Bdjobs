package com.example.myapplicationbdjobs.repository

import com.example.myapplicationbdjobs.api.endpoints.ApiService
import com.example.myapplicationbdjobs.api.models.AppTable
import com.example.myapplicationbdjobs.db.AppDao
import javax.inject.Inject

class AppRepository @Inject constructor(private val apiService: ApiService, private val appDao: AppDao) {
    suspend fun getPopularMovie()= apiService.getPopularMovie()

    suspend fun getNowShowingMovie()=apiService.getNowShowingMovie()

    suspend fun getDetailsMovie(id: Int)=apiService.getDetailsMovie(id)

    suspend fun getDbData()= appDao.getAllScheduleApps()

    suspend fun addBookmark(appTable: AppTable)=appDao.addBookmark(appTable)

    suspend fun check(id: Int)= appDao.checkIfExit(id)

    suspend fun deleteBookmark(appTable: AppTable)=appDao.deleteBookmark(appTable)

}