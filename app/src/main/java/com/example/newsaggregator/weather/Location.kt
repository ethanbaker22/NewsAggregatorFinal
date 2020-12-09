package com.example.newsaggregator.weather

/**
 * @author Ethan Baker - 986237
 * @class Location.kt
 * @version 1.0
 * Data class for Location
 */
data class Location(
    val country: String,
    val lat: Double,
    val localtime: String,
    val localtime_epoch: Int,
    val lon: Double,
    val name: String,
    val region: String,
    val tz_id: String
)