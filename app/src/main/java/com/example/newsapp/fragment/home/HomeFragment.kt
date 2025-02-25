package com.example.newsapp.fragment.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.adapter.NewsAdapter
import com.example.newsapp.data.NewsApiService
import com.example.newsapp.data.db.NewsDatabase
import com.example.newsapp.repository.NewsRepository

class HomeFragment : Fragment() {
    private lateinit var newsViewModel: NewsViewModel
    private lateinit var adapter: NewsAdapter  // RecyclerView Adapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val apiService = NewsApiService.create()
        val database = NewsDatabase.getDatabase(requireContext().applicationContext)
        val repository = NewsRepository(apiService, database.newsDao())

        newsViewModel = ViewModelProvider(this, NewsViewModelFactory(repository))[NewsViewModel::class.java]

        // Setup RecyclerView
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        adapter = NewsAdapter(requireActivity())
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        // Observe articles from ViewModel
        newsViewModel.articles.observe(viewLifecycleOwner) { articles ->
            adapter.submitList(articles)
        }

        // Refresh news on fragment start
        newsViewModel.refreshNews()
    }

}