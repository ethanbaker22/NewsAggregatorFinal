package com.example.newsaggregator.weather

/**
 * @author Ethan Baker - 986237
 * @class WeatherData.kt
 * @version 1.0
 * Data class for WeatherData
 */
data class WeatherData(
    val current: Current,
    val location: Location
)