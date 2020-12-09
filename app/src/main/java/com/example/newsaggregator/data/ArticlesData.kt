package com.example.newsaggregator.data

import com.example.newsaggregator.data.Article

/**
 *
 */
data class ArticlesData(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)