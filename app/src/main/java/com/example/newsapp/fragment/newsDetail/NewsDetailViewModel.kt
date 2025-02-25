package com.example.newsapp.fragment.newsDetail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.data.db.NewsDatabase
import com.example.newsapp.data.db.SavedArticle
import com.example.newsapp.repository.SavedRepository
import kotlinx.coroutines.launch

class NewsDetailViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: SavedRepository

    init {
        val database = NewsDatabase.getDatabase(application)
        repository = SavedRepository(database)
    }

    fun saveArticle(article: SavedArticle) {
        viewModelScope.launch {
            repository.saveArticle(article)
        }
    }

    fun deleteArticle(article: SavedArticle) {
        viewModelScope.launch {
            repository.deleteArticle(article)
        }
    }

    suspend fun isArticleSaved(url: String): Boolean {
        return repository.isArticleSaved(url)
    }
}
