package com.example.newsaggregator

import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsaggregator.adapters.MainAdapter
import com.example.newsaggregator.adapters.SearchAdapter
import com.example.newsaggregator.data.MainActivityFeed
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.fragment_home.*
import okhttp3.*
import java.io.IOException

/**
 *
 */
class Search : AppCompatActivity() {

    /**
     *
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        //
        val topToolbar = findViewById<Toolbar>(R.id.top_toolbar_search)

        setSupportActionBar(topToolbar)
        supportActionBar?.title = getString(R.string.search)
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        recyclerView_search.layoutManager = LinearLayoutManager(this)


        searchBtn.setOnClickListener {
            fetchJson()
        }

    }


    /**
     *
     */
    private fun fetchJson() {
        val userSearch = txt_search.text
        println(userSearch)

        val urlSearch =
            "https://newsapi.org/v2/top-headlines?q=$userSearch&apiKey=0f018cfe6cec48c8b84ce968752b9140"
        val urlRequestSearch = Request.Builder().url(urlSearch).build()

        val client = OkHttpClient()
        client.newCall(urlRequestSearch).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                println("Failed to execute")
            }

            /**
             *
             */
            override fun onResponse(call: Call, response: Response) {
                val body = response?.body?.string()
                println(body)

                val gson = GsonBuilder().create()
                val searchFeed = gson.fromJson(body, MainActivityFeed::class.java)

                //
                runOnUiThread {
                    recyclerView_search.adapter = SearchAdapter(searchFeed)
                }
            }
        })
    }

    /**
     *
     */
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.top_toolbar_search, menu)
        return super.onCreateOptionsMenu(menu)
    }
}