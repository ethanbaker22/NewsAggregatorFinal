package com.example.newsaggregator.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newsaggregator.R
import com.example.newsaggregator.data.MainActivityFeed
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.article_row_search.view.*

/**
 * @author Ethan Baker - 986237
 * @class SearchAdapter.kt
 * @version 1.0.1
 * The adapter for the search FAB/activity - allows the user to search through the news
 */
class SearchAdapter(val mainFeed: MainActivityFeed): RecyclerView.Adapter<CustomViewHolder>() {

    /**
     * Gets how many articles there are in that specific array
     * @return mainFeed article count
     */
    override fun getItemCount(): Int {
        return mainFeed.articles.count()
    }

    /**
     * 'Binds' the data to the TextView's or ImageView's
     * Essentially this method brings it all together
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
     * Creates the view
     * @return CustomerViewHolder - layout inflater
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val cellForRow = layoutInflater.inflate(R.layout.article_row_search, parent, false)
        return CustomViewHolder(cellForRow)
    }
}