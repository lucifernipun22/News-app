package com.example.newsapp.data.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface SavedArticleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveArticle(article: SavedArticle)

    @Delete
    suspend fun deleteArticle(article: SavedArticle)

    @Query("SELECT * FROM saved_articles")
    fun getSavedArticles(): LiveData<List<SavedArticle>>

    @Query("SELECT COUNT(*) FROM saved_articles WHERE url = :url")
    suspend fun isArticleSaved(url: String): Int
}

