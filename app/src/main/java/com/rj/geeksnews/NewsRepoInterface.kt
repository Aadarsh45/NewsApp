package com.rj.geeksnews

interface NewsRepoInterface {

    suspend fun getArticles(category:String,date:String,sortBy:String)
    suspend fun getTopHeadline(country:String)
}