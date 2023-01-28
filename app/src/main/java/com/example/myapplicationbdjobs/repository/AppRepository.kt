package com.example.myapplicationbdjobs.repository

import com.example.myapplicationbdjobs.api.endpoints.ApiService
import javax.inject.Inject

class AppRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun getPopularMovie()= apiService.getPopularMovie()
}