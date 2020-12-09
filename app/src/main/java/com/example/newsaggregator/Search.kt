package com.example.newsaggregator

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsaggregator.adapters.SearchAdapter
import com.example.newsaggregator.data.MainActivityFeed
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_search.*
import okhttp3.*
import java.io.IOException

/**
 * @author Ethan Baker - 986237
 * @class Search.kt
 * @version 1.1
 * Activity Class for Searching
 */
class Search : AppCompatActivity() {

    /**
     * When this activity is created
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        // Setting top toolbar to the specific version
        val topToolbar = findViewById<Toolbar>(R.id.top_toolbar_search)

        setSupportActionBar(topToolbar)
        supportActionBar?.title = getString(R.string.search)

        // If wanting a back arrow top left...
        // supportActionBar?.setDisplayHomeAsUpEnabled(true)

        recyclerView_search.layoutManager = LinearLayoutManager(this)
        searchBtn.setOnClickListener {
            fetchJson() // Allows communication with the database
        }

    }

    /**
     *  This is where the Json data from the api is grabbed.
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
             * What to actually do with the Json
             */
            override fun onResponse(call: Call, response: Response) {
                val body = response?.body?.string()
                println(body)

                val gson = GsonBuilder().create()
                val searchFeed = gson.fromJson(body, MainActivityFeed::class.java)

                // Sets the adapter for the Recycler View
                runOnUiThread {
                    recyclerView_search.adapter = SearchAdapter(searchFeed)
                }
            }
        })
    }

    /**
     * Sets the options mean for the toolbar
     */
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.top_toolbar_search, menu)
        return super.onCreateOptionsMenu(menu)
    }
}