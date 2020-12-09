package com.example.newsaggregator

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.newsaggregator.R.*
import com.example.newsaggregator.adapters.ForYouAdapter
import com.example.newsaggregator.adapters.MainAdapter
import com.example.newsaggregator.adapters.TabAdapter
import com.example.newsaggregator.data.MainActivityFeed
import com.example.newsaggregator.data.Users
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.fragment_for_you.*
import kotlinx.android.synthetic.main.fragment_home.*
import okhttp3.*
import java.io.IOException
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

/**
 *
 */
class MainActivity : AppCompatActivity() {

    var country = ""
    /**
     *
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)

        //
        val topToolbar = findViewById<Toolbar>(id.top_toolbar)
        setSupportActionBar(topToolbar)

        //
        val tabLayout = findViewById<TabLayout>(id.tab_layout)
        val viewPager = findViewById<ViewPager2>(id.pager)
        val tabTitles = resources.getStringArray(array.tabTitles)

        // Reads data in from Firebase
        val ref = FirebaseDatabase.getInstance().getReference("Users")
            .child(FirebaseAuth.getInstance().currentUser!!.uid)
        val logListener = object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(applicationContext, "Error reading from database", Toast.LENGTH_LONG)
                    .show()
            }

            /**
             *
             */
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val users = dataSnapshot.getValue(Users::class.java)
                val firstName = users?.firstName
                val country = users?.country
                // Welcome Message
                if (country == "err") {
                    Toast.makeText(
                        applicationContext,
                        "Welcome $firstName, please go to Settings to set your Country.",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
        ref.addListenerForSingleValueEvent(logListener)

        //
        viewPager.adapter = TabAdapter(this)
        TabLayoutMediator(
            tabLayout,
            viewPager,
            TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                when (position) {
                    0 -> tab.text = tabTitles[0]
                    1 -> tab.text = tabTitles[1]
                    2 -> tab.text = tabTitles[2]
                }
            }).attach()

        //
        val searchFab = findViewById<View>(R.id.fab_search)
        searchFab.setOnClickListener { view ->
            Toast.makeText(this, getString(string.search), Toast.LENGTH_SHORT).show()
            val intent = Intent(this@MainActivity, Search::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }

        val ref2 = FirebaseDatabase.getInstance().getReference("Users")
            .child(FirebaseAuth.getInstance().currentUser!!.uid)
        val logListener2 = object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
//                Toast.makeText(applicationContext, "Error reading from database", Toast.LENGTH_LONG)
//                    .show()
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val users = dataSnapshot.getValue(Users::class.java)
                country = users?.country.toString()
                println(country)
                fetchJson()
            }
        }
        ref2.addListenerForSingleValueEvent(logListener2)

//        fetchJson()
    }


    /**
     *
     */
    private fun fetchJson() {
        println("Attempting to fetch JSON")

        // Getting current time, formatting this so that Newsapi.org can understand it
        val current = LocalDateTime.now()
        val formatterDate = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val formatDate = current.format(formatterDate)
        val daysAgo = getDaysAgo(7)
        println(formatDate)

        //
        val urlHome = "https://newsapi.org/v2/top-headlines?country=$country&from=$daysAgo&to=$formatDate&apiKey=10cef55b087947fd844eb80ef34b3158"
//            "https://newsapi.org/v2/everything?sources=bbc-news&from=$daysAgo&to=$formatDate&sortBy=popularity&apiKey=10cef55b087947fd844eb80ef34b3158"
        val urlRequestHome = Request.Builder().url(urlHome).build()

        //
        val client = OkHttpClient()
        client.newCall(urlRequestHome).enqueue(object : Callback {
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
                val mainActivityFeed = gson.fromJson(body, MainActivityFeed::class.java)

                //
                runOnUiThread {
                    recyclerView_home.adapter = MainAdapter(mainActivityFeed)
                }
            }
        })
    }


    /**
     *
     */
    private fun getDaysAgo(daysAgo: Int): Date {
        val calender = Calendar.getInstance()
        calender.add(Calendar.DAY_OF_YEAR, -daysAgo)
        return calender.time
    }

    /**
     *
     */
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.top_toolbar_layout, menu)
        return super.onCreateOptionsMenu(menu)
    }

    /**
     *
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //
        when (item!!.itemId) {
            id.refresh -> {
                Toast.makeText(this, getString(string.refresh), Toast.LENGTH_SHORT).show()
                val intent = Intent(this@MainActivity, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT)
                startActivity(intent)
                return true
            }
            id.profile -> {
                Toast.makeText(this, getString(string.profile), Toast.LENGTH_SHORT).show()
                return true
            }
            id.settings -> {
                Toast.makeText(this, getString(string.settings), Toast.LENGTH_SHORT).show()
                val intent = Intent(this@MainActivity, Settings::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NO_ANIMATION)
                startActivity(intent)
                return true
            }
            id.logoff -> {
                Toast.makeText(this, getString(string.logoff), Toast.LENGTH_SHORT).show()
                val intent = Intent(this@MainActivity, WelcomeActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT)
                startActivity(intent)
                logOff()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    /**
     * Doesn't allow for the user to press back and still be logged in.
     * Securely logs out the user through Firebase
     */
    private fun logOff() {
        var mAuth = FirebaseAuth.getInstance()
        this.finish()
        mAuth.signOut()
    }
}