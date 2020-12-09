package com.example.newsaggregator.data

import com.example.newsaggregator.data.Article

/**
 * @author Ethan Baker - 986237
 * @class ArticleData.kt
 * @version 1.0
 * Data class for Article Data
 */
data class ArticlesData(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)