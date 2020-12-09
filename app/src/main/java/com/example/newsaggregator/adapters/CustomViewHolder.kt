package com.example.newsaggregator.adapters

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.newsaggregator.data.Article

/**
 * @author Ethan Baker - 986237
 * @class CustomViewHolder.kt
 * @version 1.0.1
 *
 * A class which pushes the data that is requested. Mainly used for when clicking on a article
 * through the ArticleView.kt class
 */
class CustomViewHolder(val view: View, var article: Article? = null) :
    RecyclerView.ViewHolder(view) {

    companion object {
        var articleHeadlineHolder = "Headline"
        var articlePublishedAtHolder = "Published"
        var articleSourceHolder = "Source"
        var articleBodyHolder = "Body"
        var articleImage = "Image"
    }

    init {
        view.setOnClickListener {
            // Loads the ArticleView class
            val intent = Intent(view.context, ArticleView::class.java)

            // I found No Animation looked the cleanest, can easily be changed for personal preference
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NO_ANIMATION)

            intent.putExtra(articleHeadlineHolder, article?.title)
            intent.putExtra(articlePublishedAtHolder, article?.publishedAt)
            intent.putExtra(articleSourceHolder, article?.source?.name)
            intent.putExtra(articleBodyHolder, article?.content)
            intent.putExtra(articleImage, article?.urlToImage)

            view.context.startActivity(intent)
        }
    }
}
