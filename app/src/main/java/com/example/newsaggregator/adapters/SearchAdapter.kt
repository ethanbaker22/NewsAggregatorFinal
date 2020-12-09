package com.example.newsaggregator.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newsaggregator.R
import com.example.newsaggregator.data.MainActivityFeed
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.article_row_search.view.*

/**
 *
 */
class SearchAdapter(val mainFeed: MainActivityFeed): RecyclerView.Adapter<CustomViewHolder>() {

    /**
     *
     */
    override fun getItemCount(): Int {
        return mainFeed.articles.count()
    }

    /**
     *
     */
    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val article = mainFeed.articles.get(position)
        val articleImage = holder?.view?.img_article_search

        holder?.itemView?.txt_article_headline_search?.text = article.title
        holder?.itemView?.txt_article_source_search?.text = article.source.name

        Picasso.get().load(article.urlToImage).placeholder(R.drawable.ic_loading_gif).fit().centerCrop().into(articleImage)

        holder?.article = article
    }

    /**
     *
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val cellForRow = layoutInflater.inflate(R.layout.article_row_search, parent, false)
        return CustomViewHolder(cellForRow)
    }
}