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
 *
 */
class FragmentForYou : Fragment() {

    var country = ""
    var preferences = ""

    /**
     *
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val ref = FirebaseDatabase.getInstance().getReference("Users")
            .child(FirebaseAuth.getInstance().currentUser!!.uid)
        val logListener = object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
//                Toast.makeText(applicationContext, "Error reading from database", Toast.LENGTH_LONG)
//                    .show()
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val users = dataSnapshot.getValue(Users::class.java)
                country = users?.country.toString()
                preferences = users?.preferences.toString()
                println("$country        $preferences")
                fetchJson()
            }
        }
        ref.addListenerForSingleValueEvent(logListener)

        return inflater.inflate(R.layout.fragment_for_you, container, false)
    }

    /**
     *
     */
    private fun fetchJson() {
        println("Attempting to fetch JSON")

        println("LOOOOOK" + country + preferences)
        val urlForYou =
            "https://newsapi.org/v2/top-headlines?country=$country&category=$preferences&apiKey=10cef55b087947fd844eb80ef34b3158"
        val urlRequestForYou = Request.Builder().url(urlForYou).build()
        println(urlForYou)

        //
        val client = OkHttpClient()
        client.newCall(urlRequestForYou).enqueue(object : Callback {
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
                    recyclerView_for_you.adapter = ForYouAdapter(mainActivityFeed)
                    showForYou(mainActivityFeed)
                }
            }
        })
    }

    /**
     *
     */
    fun Fragment?.runOnUiThread(action: () -> Unit) {
        this ?: return
        if (!isAdded) return // Fragment not attached to an Activity
        activity?.runOnUiThread(action)
    }

    fun showForYou(mainFeed: MainActivityFeed) {
        recyclerView_for_you.layoutManager = LinearLayoutManager(activity)
        recyclerView_for_you.adapter = ForYouAdapter(mainFeed)
    }
}