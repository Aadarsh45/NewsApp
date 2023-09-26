package com.rj.geeksnews.model

data class NewsResponseMain( var status:String?,
                             var totalResults:Int?,
                             var articles:ArrayList<NewsResponse>)
