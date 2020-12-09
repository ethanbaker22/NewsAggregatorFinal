package com.example.newsaggregator.adapters

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.newsaggregator.data.Article

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
            val intent = Intent(view.context, ArticleView::class.java)
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
