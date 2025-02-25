package com.example.newsapp.fragment.saved

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.newsapp.data.db.NewsDatabase
import com.example.newsapp.data.db.SavedArticle
import kotlinx.coroutines.launch

class SavedViewModel(application: Application) : AndroidViewModel(application) {
    private val savedArticleDao = NewsDatabase.getDatabase(application).savedArticleDao()

    // LiveData to observe saved articles
    val allSavedArticles: LiveData<List<SavedArticle>> =
        savedArticleDao.getSavedArticles()

    fun saveArticle(article: SavedArticle) {
        viewModelScope.launch {
            savedArticleDao.saveArticle(article)
        }
    }

    fun deleteArticle(article: SavedArticle) {
        viewModelScope.launch {
            savedArticleDao.deleteArticle(article)
        }
    }
}