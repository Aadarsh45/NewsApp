package com.rj.geeksnews.network

import com.rj.geeksnews.model.NewsResponseMain
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("everything")
    suspend fun getArticles(
        @Query("q") cat:String?,
        @Query("from") from:String?,
        @Query("to") to:String?,
        @Query("sortBy") sortBy:String?,

    ):NewsResponseMain?

    @GET("top-headlines")
    suspend fun getTopHeadline(@Query("country") country:String?):NewsResponseMain?
}