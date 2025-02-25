News App

📌 Project Description

The News App is an offline-first Android application that provides users with the latest news articles. It ensures a seamless user experience by caching articles for offline access, supporting dark mode, and providing a clean, modular codebase for easy maintenance and scalability.

🚀 Features

Offline-first architecture for accessing news without an internet connection.

Dark mode support for better user experience.

WebView integration to read full articles.

Save articles for later reading.

Bottom navigation for smooth navigation.

Data persistence using Room Database.

🛠️ Tech Stack

Kotlin - Primary programming language.

MVVM Architecture - For better code separation and maintainability.

Jetpack Components:

ViewModel - To manage UI-related data.

LiveData - For observing changes in data.

Navigation Component - For handling navigation.

Room Database - For local data persistence.

DataStore - To store user preferences (e.g., dark mode settings).

Retrofit - For API calls to fetch news data.

Coroutines & Flow - For asynchronous programming.

Glide - For image loading.

Material Components - For UI enhancements.

🏗️ Project Setup

Follow these steps to set up the project on your local machine:

Prerequisites:

Android Studio (latest version recommended)

Minimum SDK: 23

Internet connection for fetching news

Steps:

Clone the repository:

git clone https://github.com/your-repo/news-app.git
cd news-app

Open in Android Studio:

Launch Android Studio.

Open the cloned project.

Sync Gradle:

Wait for Gradle to sync dependencies.

Run the app:

Connect a device or use an emulator.

Click Run ▶️ in Android Studio.

API Configuration:

Replace YOUR_API_KEY in NewsApiService.kt with your API key from NewsAPI.

📚 Libraries Used

Library

Purpose

Retrofit

For making API requests.

Room

For local data storage.

DataStore

To store user preferences (dark mode).

ViewModel

To manage UI-related data.

LiveData

To observe data changes.

Navigation Component

To handle navigation between fragments.

Glide

For loading and caching images efficiently.

Coroutines

For handling background operations.

Material Components

For enhanced UI design.

🔍 Architecture & Design Choices

MVVM (Model-View-ViewModel)

The app follows the MVVM architecture to ensure a clean separation of concerns:

Model: Handles the data layer (Retrofit API calls, Room database, DataStore for settings).

View: UI layer (Activities, Fragments displaying data to users).

ViewModel: Acts as a bridge between View and Model, managing UI logic and LiveData.

Offline-First Approach

Uses Room Database to store news articles locally.

Fetches new articles only when connected to the internet.

Dark Mode Implementation

Uses DataStore to save user preference.

Automatically switches themes based on the stored preference.

🎯 Future Enhancements

Implement search functionality to find news by keywords.

Add push notifications for breaking news.

Enable multi-language support.

Introduce user authentication for personalized recommendations.
