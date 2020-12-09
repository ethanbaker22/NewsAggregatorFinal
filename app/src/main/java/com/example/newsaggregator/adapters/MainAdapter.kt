package com.example.newsaggregator.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newsaggregator.R
import com.example.newsaggregator.data.MainActivityFeed
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.article_row_home.view.*

/**
 *
 */
class MainAdapter(val articleFeed: MainActivityFeed) : RecyclerView.Adapter<CustomViewHolder>() {

    /**
     *
     */
    override fun getItemCount(): Int {
        return articleFeed.articles.count()
    }

    /**
     *
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.article_row_home, parent, false)
        return CustomViewHolder(cellForRow)
    }

    /**
     *
     */
    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val article = articleFeed.articles.get(position)
        val articleImage = holder?.view?.img_article_home

        holder.view.txt_article_headline_home?.text = article.title
        holder.view.txt_article_source_home?.text = article.source.name

        Picasso.get().load(article.urlToImage).placeholder(R.drawable.ic_loading_gif).fit().centerCrop().into(articleImage)

        holder?.article = article
    }
}

