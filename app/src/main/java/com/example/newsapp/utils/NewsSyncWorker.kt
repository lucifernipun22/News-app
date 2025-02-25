package com.example.newsapp.utils

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.newsapp.data.NewsApiService
import com.example.newsapp.data.db.NewsDatabase
import com.example.newsapp.repository.NewsRepository

class NewsSyncWorker(context: Context, params: WorkerParameters) :
    CoroutineWorker(context, params) {
    override suspend fun doWork(): Result {
        val db = NewsDatabase.getDatabase(applicationContext)
        val apiService = NewsApiService.create()
        val repository = NewsRepository(apiService, db.newsDao())

        return try {
            repository.refreshNews()
            Result.success()
        } catch (e: Exception) {
            Result.retry()
        }
    }
}
