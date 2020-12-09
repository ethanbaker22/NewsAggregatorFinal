package com.example.newsaggregator.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.newsaggregator.R
import com.example.newsaggregator.weather.WeatherData
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.fragment_weather.*
import kotlinx.android.synthetic.main.fragment_weather.view.*
import okhttp3.*
import java.io.IOException

/**
 * @author Ethan Baker - 986237
 * @class FragmentWeather.kt
 * @version 1.0
 * Fragment class for the Weather tab
 */
class FragmentWeather : Fragment(R.layout.fragment_weather) {

    /**
     * Defines what happens when the tab is selected
     * @return view - basically generates the fragment
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        val view: View = inflater.inflate(R.layout.fragment_weather, container, false)
        view.searchBtn.setOnClickListener {
            fetchJson()
        }
        return view

    }

    /**
     *  This is where the Json data from the api is grabbed.
     */
    private fun fetchJson() {
        val country = "Swansea" // Hard coded, but user should be able to search
        println("Attempting to fetch JSON")

        val urlWeather = "http://api.weatherapi.com/v1/current.json?key=284f892e5564405897e00824200912&q=$country"
        val urlRequestWeather = Request.Builder().url(urlWeather).build()

        // What to do with the HTTP link of Json data
        val client = OkHttpClient()
        client.newCall(urlRequestWeather).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                println("failed to execute")
            }

            /**
            * What to actually do with the Json
            */
            override fun onResponse(call: Call, response: Response) {
                val gson = GsonBuilder().create()
                val body = response.body?.string()
                val weatherFeed = gson.fromJson(body, WeatherData::class.java)

                // Sets up an adapter
                runOnUiThread {
                    txt_weather.text = weatherFeed.current.toString()
                }
                println(body)
            }
        })
    }

    /**
     * This is needed for runUiThread to work in a fragment - activity does it automatically
     */
    fun Fragment?.runOnUiThread(action: () -> Unit) {
        this ?: return
        if (!isAdded) return
        activity?.runOnUiThread(action)
    }
}