package com.example.newsapp.fragment.saved

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.adapter.SavedArticlesAdapter

class SavedFragment : Fragment() {

    private val viewModel: SavedViewModel by viewModels()
    private lateinit var adapter: SavedArticlesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sved, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        adapter = SavedArticlesAdapter({ article ->
            viewModel.deleteArticle(article)
        }, requireActivity())
        recyclerView.adapter = adapter

        // Observe saved articles and update RecyclerView
        viewModel.allSavedArticles.observe(viewLifecycleOwner) { articles ->
            adapter.submitList(articles)
        }
    }

}