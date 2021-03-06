package com.example.newsaggregator.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newsaggregator.R
import com.example.newsaggregator.data.MainActivityFeed
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.article_row_for_you.view.*

/**
 * @author Ethan Baker - 986237
 * @class ForYouAdapter.kt
 * @version 1.2.2
 * For You Adapter gets the news information for the For You tab
 */
class ForYouAdapter(val mainFeed: MainActivityFeed): RecyclerView.Adapter<CustomViewHolder>() {

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
        val articleImage = holder?.view?.img_article_for_you

        holder.itemView?.txt_article_headline_for_you?.text = article.title
        holder.itemView?.txt_article_source_for_you?.text = article.source.name

        Picasso.get().load(article.urlToImage).placeholder(R.drawable.ic_loading_gif).fit().centerCrop().into(articleImage)

        holder?.article = article
    }

    /**
     * Creates the view
     * @return CustomerViewHolder - layout inflater
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val cellForRow = layoutInflater.inflate(R.layout.article_row_for_you, parent, false)
        return CustomViewHolder(cellForRow)
    }
}


