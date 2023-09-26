package com.rj.geeksnews

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rj.geeksnews.model.NewsResponseMain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsViewModel:ViewModel() {

    private val mRepository = NewsRepo()

    var mArticlesData: LiveData<NewsResponseMain> = mRepository.getArticlesData()
    var mTopHeadlinesData: LiveData<NewsResponseMain> = mRepository.getTopHeadlinesData()


    fun getArticles(category:String,date:String,sortBy:String)=

        viewModelScope.launch (Dispatchers.IO){
            mRepository.getArticles(category,date,sortBy)
        }

    fun getTopHeadline(country:String)  =   viewModelScope.launch (Dispatchers.IO){
        mRepository.getTopHeadline(country)
    }
}