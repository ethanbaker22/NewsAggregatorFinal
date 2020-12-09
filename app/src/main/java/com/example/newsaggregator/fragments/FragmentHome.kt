package com.example.newsaggregator.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsaggregator.R

/**
 * @author Ethan Baker - 986237
 * @class FragmentHome.kt
 * @version 1.0
 * Fragment class for the Home tab
 */
class FragmentHome : Fragment() {

    /**
     * Most code is set out in MainActivity as its the first thing that loads. This is here to set
     * up the recyclerView's Layout Manager
     * @return listItems - Inflates the fragment
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val listItems: View = inflater.inflate(R.layout.fragment_home, container, false)!!
        val recyclerView = listItems.findViewById<View>(R.id.recyclerView_home) as RecyclerView

        recyclerView.layoutManager = LinearLayoutManager(this.context)
        return listItems
    }
}