package com.example.myapplicationbdjobs.api.endpoints

import com.example.myapplicationbdjobs.api.ErrorResponse
import com.example.myapplicationbdjobs.api.models.PopularResponse
import com.haroldadmin.cnradapter.NetworkResponse
import retrofit2.http.GET

interface ApiService {
    @GET("3/movie/popular?api_key=98f3908014410fc8a0a0393df1b060af&language=en-US&page=1")
    suspend fun getPopularMovie(): NetworkResponse<PopularResponse, ErrorResponse>
}