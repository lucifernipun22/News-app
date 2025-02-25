package com.example.newsapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.R
import com.example.newsapp.data.db.SavedArticle
import com.example.newsapp.fragment.newsDetail.NewsDetailFragment

class SavedArticlesAdapter(
    private val onDeleteClick: (SavedArticle) -> Unit,
    private val context: FragmentActivity,

    ) : ListAdapter<SavedArticle, SavedArticlesAdapter.ArticleViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_saved_article, parent, false)
        return ArticleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = getItem(position)
        holder.bind(article)
    }

    inner class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.newsTitle)
        private val descTextView: TextView = itemView.findViewById(R.id.newsDescription)
        private val ivImage: ImageView = itemView.findViewById(R.id.newsImage)
        private val root: ConstraintLayout = itemView.findViewById(R.id.root)

        private val deleteButton: ImageButton = itemView.findViewById(R.id.btnDelete)

        fun bind(article: SavedArticle) {
            titleTextView.text = article.title
            descTextView.text = article.description
            Glide.with(ivImage).load(article.imageUrl).into(ivImage)
            deleteButton.setOnClickListener { onDeleteClick(article) }

            // Handle item click to open NewsDetailFragment
            root.setOnClickListener {
                val fragment = NewsDetailFragment.newInstance(
                    article.url,
                    article.title ?: "No Title",
                    article.description ?: "No Description",
                    article.imageUrl ?: ""
                )
                context.supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .addToBackStack(null)
                    .commit()
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<SavedArticle>() {
        override fun areItemsTheSame(oldItem: SavedArticle, newItem: SavedArticle) =
            oldItem.url == newItem.url

        override fun areContentsTheSame(oldItem: SavedArticle, newItem: SavedArticle) =
            oldItem == newItem
    }
}
