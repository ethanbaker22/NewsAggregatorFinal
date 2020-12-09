package com.example.newsaggregator.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newsaggregator.R
import com.example.newsaggregator.data.MainActivityFeed
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.article_row_for_you.view.*
import kotlinx.android.synthetic.main.article_row_home.view.*

/**
 *
 */
class ForYouAdapter(val mainFeed: MainActivityFeed): RecyclerView.Adapter<CustomViewHolder>() {

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
        val articleImage = holder?.view?.img_article_for_you

        holder.itemView?.txt_article_headline_for_you?.text = article.title
        holder.itemView?.txt_article_source_for_you?.text = article.source.name

        Picasso.get().load(article.urlToImage).placeholder(R.drawable.ic_loading_gif).fit().centerCrop().into(articleImage)

        holder?.article = article
    }

    /**
     *
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val cellForRow = layoutInflater.inflate(R.layout.article_row_for_you, parent, false)
        return CustomViewHolder(cellForRow)
    }
}


