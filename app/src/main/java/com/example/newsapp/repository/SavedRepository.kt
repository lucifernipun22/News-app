package com.example.newsapp.repository

import com.example.newsapp.data.db.SavedArticle
import com.example.newsapp.data.db.NewsDatabase

class SavedRepository(private val database: NewsDatabase) {

    private val savedArticleDao = database.savedArticleDao()

    suspend fun saveArticle(article: SavedArticle) {
        savedArticleDao.saveArticle(article)
    }

    suspend fun deleteArticle(article: SavedArticle) {
        savedArticleDao.deleteArticle(article)
    }

    suspend fun isArticleSaved(url: String): Boolean {
        return savedArticleDao.isArticleSaved(url) > 0
    }

    fun getAllSavedArticles() = savedArticleDao.getSavedArticles() // LiveData/Flow for UI updates
}
