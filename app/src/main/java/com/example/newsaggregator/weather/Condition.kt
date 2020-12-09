package com.example.newsaggregator.weather

/**
 * @author Ethan Baker - 986237
 * @class Current.kt
 * @version 1.0
 * Data class for Condition
 */
data class Condition(
    val code: Int,
    val icon: String,
    val text: String
)