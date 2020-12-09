package com.example.newsaggregator.data

/**
 * @author Ethan Baker - 986237
 * @class Article.kt
 * @version 1.0
 * Data class for Article
 */
data class Article(
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: Source,
    val title: String,
    val url: String,
    val urlToImage: String
)