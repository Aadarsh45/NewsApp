package com.rj.geeksnews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rj.geeksnews.adapter.CategoryAdapter
import com.rj.geeksnews.adapter.NewsAdapter
import com.rj.geeksnews.databinding.ActivityMainBinding
import com.rj.geeksnews.model.CategoryResponse
import java.text.SimpleDateFormat
import java.util.Date

class MainActivity : AppCompatActivity(),CategoryAdapter.CategoryClickListener {
    private lateinit var mBinding:ActivityMainBinding
    private var mViewModel:NewsViewModel?=null
    private lateinit var newsAdapter: NewsAdapter
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var categoryList:ArrayList<CategoryResponse>
    private val SORT_BY:String="popularity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        categoryList = ArrayList()
        newsAdapter = NewsAdapter(this)
        categoryAdapter = CategoryAdapter(this,categoryList)

        mViewModel = ViewModelProvider(this).get(NewsViewModel::class.java)

        mBinding.categoryList.layoutManager = LinearLayoutManager(this,RecyclerView.HORIZONTAL,false)
        mBinding.categoryList.adapter = categoryAdapter

        mBinding.rvNewList.layoutManager = LinearLayoutManager(this,RecyclerView.HORIZONTAL,false)
        mBinding.rvNewList.adapter = newsAdapter

        setData()
        setupObserver()
        mViewModel?.getTopHeadline("in")

    }
    private fun setupObserver(){
        mViewModel?.mTopHeadlinesData?.observe(this){
           it.let {
               mBinding.rvNewList.scrollToPosition(0)
               newsAdapter.addItems(it.articles)
           }
        }
        mViewModel?.mArticlesData?.observe(this){
            it.let {
                mBinding.rvNewList.scrollToPosition(0)
                newsAdapter.addItems(it.articles)
            }
        }
    }

    override fun onTabChange(tabId: Int?) {
        when(tabId){
            1 ->mViewModel?.getTopHeadline("in")
            2 ->mViewModel?.getArticles("business",getCurrentDate(),SORT_BY)
            3 ->mViewModel?.getArticles("bitcoin",getCurrentDate(),SORT_BY)
            4 ->mViewModel?.getArticles("android",getCurrentDate(),SORT_BY)
            5 ->mViewModel?.getArticles("technology",getCurrentDate(),SORT_BY)
        }
    }

    private fun getCurrentDate():String{
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        return sdf.format(Date())

    }
    private fun setData(){
        categoryList.add(CategoryResponse(1,"Top Heading",true))
        categoryList.add(CategoryResponse(2,"Top Business"))
        categoryList.add(CategoryResponse(3,"Bitcoin"))
        categoryList.add(CategoryResponse(4,"Android"))
        categoryList.add(CategoryResponse(5,"Technology"))


    }
}