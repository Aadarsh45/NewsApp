package com.rj.geeksnews

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rj.geeksnews.model.NewsResponseMain
import com.rj.geeksnews.network.ApiInterface
import com.rj.geeksnews.network.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NewsRepo:NewsRepoInterface {

    private val mApiInterface:ApiInterface?=RetrofitClient.RetrofitInstance.getClient()?.create(ApiInterface::class.java)

    private val _articlesData = MutableLiveData<NewsResponseMain>()
    private val _topHeadlinesData = MutableLiveData<NewsResponseMain>()

    fun getArticlesData():LiveData<NewsResponseMain> = _articlesData
    fun getTopHeadlinesData():LiveData<NewsResponseMain> = _topHeadlinesData
    var job:Job?=null
    override suspend fun getArticles(category: String, date: String, sortBy: String) {
     job= CoroutineScope(Dispatchers.IO).launch {
         val  response = mApiInterface?.getArticles(category,"2023-07-06",date,sortBy)
         withContext(Dispatchers.Main){
             if (response?.status=="ok"){
                 _articlesData.postValue(response!!)
             }else{
                 onError("Error: ${response?.status}")
             }
         }
     }
    }

    override suspend fun getTopHeadline(country: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val  response = mApiInterface?.getTopHeadline(country)
            withContext(Dispatchers.Main){
                if (response?.status=="ok"){
                    _topHeadlinesData.postValue(response!!)
                }else{
                    onError("Error: ${response?.status}")
                }
            }
        }
    }

    private fun onError(message:String){
        Log.e("TAG","onError: $message")
    }
}