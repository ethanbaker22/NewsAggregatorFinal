package com.example.newsaggregator.adapters

import android.os.Bundle
import android.os.PersistableBundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.newsaggregator.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_article_view.*
import kotlinx.android.synthetic.main.article_row_home.view.*

/**
 *
 */
class ArticleView : AppCompatActivity() {

    /**
     *
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_view)

        val topToolbar = findViewById<Toolbar>(R.id.top_toolbar)
        setSupportActionBar(topToolbar)

        val articleImage = img_article_view

        println("hello!")
        Picasso.get().load(intent.getStringExtra(CustomViewHolder.articleImage)).fit().centerCrop().placeholder(R.drawable.ic_loading_gif).into(articleImage)
        txt_article_headline_view.text = intent.getStringExtra(CustomViewHolder.articleHeadlineHolder)
        txt_article_published_view.text = intent.getStringExtra(CustomViewHolder.articlePublishedAtHolder)
        txt_article_body_view.text = intent.getStringExtra(CustomViewHolder.articleBodyHolder)
        txt_article_source_view.text = intent.getStringExtra(CustomViewHolder.articleSourceHolder)
    }

    /**
     *
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_toolbar_search, menu)
        return super.onCreateOptionsMenu(menu)
    }
}