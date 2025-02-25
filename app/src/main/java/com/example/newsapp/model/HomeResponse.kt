package com.example.newsapp.model

data class HomeResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)