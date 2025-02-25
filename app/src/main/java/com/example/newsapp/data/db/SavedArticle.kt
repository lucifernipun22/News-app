package com.example.newsapp.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.newsapp.model.Article


@Entity(tableName = "saved_articles")
data class SavedArticle(
    @PrimaryKey(autoGenerate = false)
    val url: String,  // Using URL as primary key to prevent duplicates
    val title: String,
    val description: String,
    val imageUrl: String
)

