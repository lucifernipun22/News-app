package com.example.newsapp.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.newsapp.data.NewsApiService
import com.example.newsapp.data.db.ArticleEntity
import com.example.newsapp.data.db.NewsDao
import com.example.newsapp.data.db.toEntity
import com.example.newsapp.model.Article
import com.example.newsapp.model.NewsResponse

class NewsRepository(private val apiService: NewsApiService, private val newsDao: NewsDao) {

    // Directly get LiveData from Room (returns List<ArticleEntity>)
    val allArticles: LiveData<List<ArticleEntity>> = newsDao.getAllArticles()

    suspend fun refreshNews() {
        try {
            val response = apiService.getNews()
            newsDao.clearArticles()  // Remove old data
            newsDao.insertArticles(response.articles.map { it.toEntity() })
        } catch (e: Exception) {
            Log.e("NewsRepository", "Error fetching news: ${e.message}")
        }
    }
}
