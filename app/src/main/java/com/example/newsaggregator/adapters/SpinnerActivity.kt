package com.example.newsaggregator.adapters

import android.app.Activity
import android.view.View
import android.widget.AdapterView

/**
 * @author Ethan Baker - 986237
 * @class SpinnerActivity.kt
 * @version 1.0
 * Gets the item selected in a Spinner
 */
class SpinnerActivity : Activity(), AdapterView.OnItemSelectedListener {

    override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
        // An item was selected. You can retrieve the selected item using
        parent.getItemAtPosition(pos)
    }

    // I set default values when the user signs up
    override fun onNothingSelected(parent: AdapterView<*>) {
    }
}