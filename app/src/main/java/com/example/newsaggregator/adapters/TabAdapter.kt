package com.example.newsaggregator.adapters

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.newsaggregator.FragmentHome
import com.example.newsaggregator.fragments.FragmentForYou
//import com.example.newsaggregator.fragments.FragmentTopStories
import com.example.newsaggregator.fragments.FragmentWeather

/**
 *
 */
class TabAdapter (activity: AppCompatActivity) : FragmentStateAdapter(activity){

    /**
     *
     */
    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return FragmentHome()
            1 -> return FragmentForYou()
            2 -> return FragmentWeather()
        }
        return FragmentHome()
    }

    /**
     *
     */
    override fun getItemCount(): Int {
        return 3
    }
}