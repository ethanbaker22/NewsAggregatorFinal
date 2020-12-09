package com.example.newsaggregator.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsaggregator.R
import com.example.newsaggregator.adapters.ForYouAdapter
import com.example.newsaggregator.data.MainActivityFeed
import com.example.newsaggregator.data.Users
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.fragment_for_you.*
import okhttp3.*
import java.io.IOException

/**
 * @author Ethan Baker - 986237
 * @class FragmentForYou.kt
 * @version 1.1
 * Fragment class for the For You tab
 */
class FragmentForYou : Fragment() {

    // Variables which get updated by the user's sections from the Firebase database
    var country = ""
    var preferences = ""

    /**
     * Defines what happens when the tab is selected
     * @return fragment inflater, basically generates the fragment
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val ref = FirebaseDatabase.getInstance().getReference("Users")
            .child(FirebaseAuth.getInstance().currentUser!!.uid)
        val logListener = object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                // Empty
            }

            /**
             * What to do when the data changes/is requested
             */
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val users = dataSnapshot.getValue(Users::class.java)
                country = users?.country.toString()
                preferences = users?.preferences.toString()
                fetchJson() // Called here so  preferences can be set first
            }
        }
        ref.addListenerForSingleValueEvent(logListener)
        return inflater.inflate(R.layout.fragment_for_you, container, false)
    }

    /**
     *  This is where the Json data from the api is grabbed.
     */
    private fun fetchJson() {
        println("Attempting to fetch JSON")

        // $country & $preferences from database
        val urlForYou =
            "https://newsapi.org/v2/top-headlines?country=$country&category=$preferences&apiKey=0f018cfe6cec48c8b84ce968752b9140"
        val urlRequestForYou = Request.Builder().url(urlForYou).build() //URL builder

        // What to do with the HTTP link of Json data
        val client = OkHttpClient()
        client.newCall(urlRequestForYou).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                println("Failed to execute")
            }

            /**
             * What to actually do with the Json
             */
            override fun onResponse(call: Call, response: Response) {
                val body = response?.body?.string()
                val gson = GsonBuilder().create()
                val mainActivityFeed = gson.fromJson(body, MainActivityFeed::class.java)

                // Sets up an adapter
                runOnUiThread {
                    recyclerView_for_you.adapter = ForYouAdapter(mainActivityFeed)
                    showForYou(mainActivityFeed)
                }
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

    /**
     * Setting up Layout Manager and Adapter
     */
    fun showForYou(mainFeed: MainActivityFeed) {
        recyclerView_for_you.layoutManager = LinearLayoutManager(activity)
        recyclerView_for_you.adapter = ForYouAdapter(mainFeed)
    }
}