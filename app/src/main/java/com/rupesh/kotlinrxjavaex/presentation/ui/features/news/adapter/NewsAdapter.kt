package com.rupesh.kotlinrxjavaex.presentation.ui.features.news.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.rupesh.kotlinrxjavaex.R
import com.rupesh.kotlinrxjavaex.data.news.model.NewsArticle
import com.rupesh.kotlinrxjavaex.databinding.NewsListItemBinding

class NewsAdapter(
    val listenerForClick: (article: NewsArticle) -> Unit
): RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding: NewsListItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.news_list_item,
            parent,
            false
        )
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val newsArticle = differ.currentList[position]
        holder.bind(newsArticle)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class NewsViewHolder(val binding: NewsListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(article: NewsArticle) {
            binding.newsArticle = article
            onNewsItemClick()
        }

        private fun onNewsItemClick() {
            binding.mainLayoutNews.setOnClickListener {
                val position = adapterPosition

                if(position != RecyclerView.NO_POSITION) {
                    val selectedArticle = differ.currentList[position]
                    listenerForClick(selectedArticle)
                } else {
                    Toast.makeText(binding.root.context, "Internal error", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private val diffCallback = object : DiffUtil.ItemCallback<NewsArticle>() {
        // DiffUtil uses this function to decide whether two objects are the same
        override fun areItemsTheSame(oldItem: NewsArticle, newItem: NewsArticle): Boolean {
            return oldItem.title == newItem.title
        }

        // DiffUtil uses this function to decide whether two object have the same data
        override fun areContentsTheSame(oldItem: NewsArticle, newItem: NewsArticle): Boolean {
            return oldItem == newItem
        }
    }

    // It computes the List contents via DiffUtil on a background thread as new Lists are received
    val differ = AsyncListDiffer(this, diffCallback)
}