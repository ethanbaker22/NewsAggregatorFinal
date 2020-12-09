package com.example.newsaggregator.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.newsaggregator.R
import com.example.newsaggregator.weather.WeatherData
//import com.example.newsaggregator.weather.response.WeatherResponse
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.fragment_weather.*
import kotlinx.android.synthetic.main.fragment_weather.view.*
import okhttp3.*
import java.io.IOException


class FragmentWeather : Fragment(R.layout.fragment_weather) {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view: View = inflater.inflate(R.layout.fragment_weather, container, false)
        view.searchBtn.setOnClickListener { view ->
            fetchJSon()
            println("buttonclick")
        }
        return view

    }

    fun fetchJSon() {

        val country = "London"
        println("attempting to fetch json")

        val url = " http://api.weatherapi.com/v1/current.json?key=284f892e5564405897e00824200912&q=$country"
        val request = Request.Builder().url(url).build()
        val client = OkHttpClient()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                println("failed to execute")
            }

            override fun onResponse(call: Call, response: Response) {
                val gson = GsonBuilder().create()
                val body = response.body?.string()


                val weatherFeed = gson.fromJson(body, WeatherData::class.java)
                runOnUiThread {
                    txt_weather.text = weatherFeed.current.toString()
                }

                println(body)
            }
        })
    }

    fun Fragment?.runOnUiThread(action: () -> Unit) {
        this ?: return
        if (!isAdded) return
        activity?.runOnUiThread(action)
    }

}

