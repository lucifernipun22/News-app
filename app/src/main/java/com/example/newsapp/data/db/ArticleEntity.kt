package com.example.newsapp.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.newsapp.model.Article
import com.example.newsapp.model.Source

@Entity(tableName = "articles")
data class ArticleEntity(
    @PrimaryKey val url: String,
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String,
    val sourceName: String,
    val title: String? ="",
    val urlToImage: String?
)


// Convert API model to Room entity
fun Article.toEntity(): ArticleEntity {
    return ArticleEntity(
        url = this.url,
        author = this.author,
        content = this.content,
        description = this.description,
        publishedAt = this.publishedAt,
        sourceName = this.source.name,
        title = this.title,
        urlToImage = this.urlToImage
    )
}

// Convert Room entity back to API model (for UI)
fun ArticleEntity.toArticle(): Article {
    return Article(
        url = this.url,
        author = this.author,
        content = this.content,
        description = this.description,
        publishedAt = this.publishedAt,
        source = Source(id = "", name = this.sourceName),
        title = this.title,
        urlToImage = this.urlToImage
    )
}

