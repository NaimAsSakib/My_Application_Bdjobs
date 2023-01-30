package com.example.myapplicationbdjobs.api.endpoints

import com.example.myapplicationbdjobs.api.ErrorResponse
import com.example.myapplicationbdjobs.api.models.PopularResponse
import com.example.myapplicationbdjobs.api.models.details.DetailsResponse
import com.example.myapplicationbdjobs.api.models.home.now_showing.NowShowingResponse
import com.haroldadmin.cnradapter.NetworkResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("3/movie/popular?api_key=98f3908014410fc8a0a0393df1b060af&language=en-US&page=1")
    suspend fun getPopularMovie(): NetworkResponse<PopularResponse, ErrorResponse>

    @GET("3/movie/now_playing?api_key=98f3908014410fc8a0a0393df1b060af&language=en-US&page=1")
    suspend fun getNowShowingMovie(): NetworkResponse<NowShowingResponse, ErrorResponse>

    @GET("/3/movie/{movie_id}?api_key=98f3908014410fc8a0a0393df1b060af&language=en-US")
    suspend fun getDetailsMovie(@Path ("movie_id") id: Int): NetworkResponse<DetailsResponse, ErrorResponse>
}