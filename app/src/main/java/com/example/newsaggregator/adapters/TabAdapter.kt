package com.example.newsaggregator.adapters

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.newsaggregator.fragments.FragmentHome
import com.example.newsaggregator.fragments.FragmentForYou
import com.example.newsaggregator.fragments.FragmentWeather

/**
 * @author Ethan Baker - 986237
 * @class TabAdapter.kt
 * @version 1.0
 * Sets up the fragment tabs
 */
class TabAdapter (activity: AppCompatActivity) : FragmentStateAdapter(activity){

    /**
     * Creates the fragments. Here they have been hard coded as there is only 3
     * @return FragmentHome is the home page
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
     * Returns the number of Fragments
     */
    override fun getItemCount(): Int {
        return 3
    }
}