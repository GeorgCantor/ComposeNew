package com.example.domain.model

data class News(
    val page: Int = 1,
    var status: String,
    var totalResults: Int,
    var articles: List<Article>
)