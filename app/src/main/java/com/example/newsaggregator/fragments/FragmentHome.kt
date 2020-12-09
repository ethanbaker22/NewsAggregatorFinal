package com.example.newsaggregator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsaggregator.adapters.MainAdapter
import kotlinx.android.synthetic.main.fragment_home.*

class FragmentHome : Fragment() {


    /**
     *
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val listItems : View = inflater.inflate(R.layout.fragment_home, container, false)!!
        val recyclerView =  listItems.findViewById<View>(R.id.recyclerView_home) as RecyclerView

        recyclerView.layoutManager = LinearLayoutManager(this.context)
//        recyclerView.adapter = MainAdapter(Mai)

        return listItems
    }

}