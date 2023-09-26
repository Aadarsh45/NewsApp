package com.rj.geeksnews.model

import com.google.gson.annotations.SerializedName

data class NewsResponse(
    //var source:Source,
    var author: String?,
    var title: String?,
    var url: String?,
    var urlToImage: String?
)


data class Source(
    var id: String?,
    var name: String?
)

