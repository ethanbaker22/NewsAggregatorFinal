//package com.example.newsaggregator.fragments
//
//import android.os.Bundle
//import androidx.fragment.app.Fragment
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.recyclerview.widget.LinearLayoutManager
//import com.example.newsaggregator.R
//import com.example.newsaggregator.adapters.MainAdapter
//import com.example.newsaggregator.adapters.TopStoriesAdapter
//import com.example.newsaggregator.data.Article
//import com.example.newsaggregator.data.MainActivityFeed
//import com.google.gson.GsonBuilder
//import kotlinx.android.synthetic.main.fragment_home.*
//import kotlinx.android.synthetic.main.fragment_top_stories.*
//import okhttp3.*
//import java.io.IOException
//import java.time.LocalDateTime
//import java.time.format.DateTimeFormatter
//
///**
// *
// */
//class FragmentTopStories : Fragment() {
//
//    /**
//     *
//     */
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
////        fetchJson()
//        return inflater.inflate(R.layout.fragment_top_stories, container, false)
//    }
//
//    /**
//     *
//     */
//    private fun fetchJson() {
//        println("Attempting to fetch JSON")
//        val urlTopStories =
//            "https://newsapi.org/v2/top-headlines?country=gb&apiKey=10cef55b087947fd844eb80ef34b3158"
//        val urlRequestTopStories = Request.Builder().url(urlTopStories).build()
//
//        //
//        val client = OkHttpClient()
//        client.newCall(urlRequestTopStories).enqueue(object : Callback {
//            override fun onFailure(call: Call, e: IOException) {
//                println("Failed to execute")
//            }
//
//            /**
//             *
//             */
//            override fun onResponse(call: Call, response: Response) {
//                val body = response?.body?.string()
//                println(body)
//
//                val gson = GsonBuilder().create()
//                val mainActivityFeed = gson.fromJson(body, MainActivityFeed::class.java)
//
//                //
//                runOnUiThread {
//                    recyclerView_top_stories.adapter = TopStoriesAdapter(mainActivityFeed)
//                    showTopStories(mainActivityFeed)
//                }
//            }
//        })
//    }
//
//    /**
//     *
//     */
//    fun Fragment?.runOnUiThread(action: () -> Unit) {
//        this ?: return
//        if (!isAdded) return // Fragment not attached to an Activity
//        activity?.runOnUiThread(action)
//    }
//
//    /**
//     *
//     */
//    fun showTopStories(mainFeed: MainActivityFeed) {
//        recyclerView_top_stories.layoutManager = LinearLayoutManager(activity)
//        recyclerView_top_stories.adapter = TopStoriesAdapter(mainFeed)
//    }
//}