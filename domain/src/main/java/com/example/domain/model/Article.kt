package com.example.domain.model

data class Article(
    var id: Long = 0,
    var title: String,
    var description: String,
    var url: String,
    var urlToImage: String?,
    var publishedAt: String,
    var content: String
)