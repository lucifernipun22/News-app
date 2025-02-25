package com.example.newsapp.fragment.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.newsapp.model.Article
import com.example.newsapp.repository.NewsRepository
import kotlinx.coroutines.launch
import androidx.lifecycle.map
import com.example.newsapp.data.db.toArticle

class NewsViewModel(private var repository: NewsRepository) : ViewModel() {

    // Convert LiveData<List<ArticleEntity>> to LiveData<List<Article>>
    val articles: LiveData<List<Article>> = repository.allArticles.map { entities ->
        entities.map { it.toArticle() } // Convert each entity to Article
    }

    fun refreshNews() {
        viewModelScope.launch {
            repository.refreshNews()
        }
    }
}


class NewsViewModelFactory(private val repository: NewsRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return NewsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

