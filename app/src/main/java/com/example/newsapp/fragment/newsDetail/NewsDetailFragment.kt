package com.example.newsapp.fragment.newsDetail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.newsapp.R
import com.example.newsapp.data.db.SavedArticle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.launch

class NewsDetailFragment : Fragment() {

    private var isSaved = false
    private var articleUrl: String = ""
    private var articleTitle: String = ""
    private var articleDescription: String = ""
    private var articleImageUrl: String = ""
    private lateinit var fabSave: FloatingActionButton
    private val newsDetailViewModel: NewsDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_news_detail, container, false)
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val webView: WebView = view.findViewById(R.id.webView)
         fabSave = view.findViewById(R.id.fabSave)
        // Get data from arguments
        articleUrl = arguments?.getString("NEWS_URL") ?: ""
        articleTitle = arguments?.getString("NEWS_TITLE") ?: "Unknown Title"
        articleDescription = arguments?.getString("NEWS_DESC") ?: "No description available"
        articleImageUrl = arguments?.getString("NEWS_IMAGE") ?: ""

        // Enable JavaScript & WebView settings
        webView.settings.javaScriptEnabled = true
        webView.settings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK

        // Load the URL in WebView
        webView.webViewClient = WebViewClient()
        webView.loadUrl(articleUrl)
        // Check if article is already saved
        lifecycleScope.launch {
            isSaved = newsDetailViewModel.isArticleSaved(articleUrl)
            updateFabState()
        }

        // Handle Save Button Click
        fabSave.setOnClickListener {
            if (isSaved) {
                deleteArticle()
            } else {
                saveArticle()
            }
        }
    }

    private fun saveArticle() {
        val article = SavedArticle(articleUrl, articleTitle, articleDescription, articleImageUrl)
        newsDetailViewModel.saveArticle(article)
        isSaved = true
        updateFabState()
    }

    private fun deleteArticle() {
        val article = SavedArticle(articleUrl, articleTitle, articleDescription, articleImageUrl)
        newsDetailViewModel.deleteArticle(article)
        isSaved = false
        updateFabState()
    }

    private fun updateFabState() {
        if (isSaved) {
            fabSave.setBackgroundDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.circle_background))
            fabSave.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.primary)
            fabSave.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_saved_white))
            fabSave.imageTintList = null // Ensures correct icon color
        } else {
            fabSave.setBackgroundDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.circle_background))
            fabSave.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.secondary)
            fabSave.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_saved_black))
            fabSave.imageTintList = null // Ensures correct icon color
        }
    }


    companion object {
        fun newInstance(url: String, title: String, description: String, imageUrl: String): NewsDetailFragment {
            return NewsDetailFragment().apply {
                arguments = Bundle().apply {
                    putString("NEWS_URL", url)
                    putString("NEWS_TITLE", title)
                    putString("NEWS_DESC", description)
                    putString("NEWS_IMAGE", imageUrl)
                }
            }
        }
    }
}
