package com.example.newsapp.utils

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

object DarkModeManager {
    private val Context.dataStore by preferencesDataStore("settings")

    private val DARK_MODE_KEY = booleanPreferencesKey("dark_mode")

    suspend fun setDarkMode(context: Context, enabled: Boolean) {
        context.dataStore.edit { it[DARK_MODE_KEY] = enabled }
    }

    suspend fun isDarkModeEnabled(context: Context): Boolean {
        return context.dataStore.data.map { it[DARK_MODE_KEY] ?: false }.first()
    }
}

