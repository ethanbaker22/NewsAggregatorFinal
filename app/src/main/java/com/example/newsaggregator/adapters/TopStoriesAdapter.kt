//package com.example.newsaggregator.adapters
//
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.recyclerview.widget.RecyclerView
//import com.example.newsaggregator.R
//import com.example.newsaggregator.data.MainActivityFeed
//import kotlinx.android.synthetic.main.article_row_home.view.*
//import kotlinx.android.synthetic.main.article_row_top_stories.view.*
//
///**
// *
// */
//class TopStoriesAdapter(val mainFeed: MainActivityFeed): RecyclerView.Adapter<CustomViewHolder>() {
//
//    /**
//     *
//     */
//    override fun getItemCount(): Int {
//        return mainFeed.articles.count()
//    }
//
//    /**
//     *
//     */
//    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
//        val article = mainFeed.articles.get(position)
//        holder?.itemView?.txt_article_headline_top_stories?.text = article.title
//        holder?.itemView?.txt_article_source_top_stories?.text = article.source.name
//    }
//
//    /**
//     *
//     */
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
//        val layoutInflater = LayoutInflater.from(parent?.context)
//        val cellForRow = layoutInflater.inflate(R.layout.article_row_top_stories, parent, false)
//        return CustomViewHolder(cellForRow)
//    }
//}
//
//
