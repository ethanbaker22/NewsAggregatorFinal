package com.example.newsaggregator

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.newsaggregator.data.Users
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_settings.*

/**
 *
 */
class Settings : AppCompatActivity() {

    private var users = Users()
    private lateinit var mAuth: FirebaseAuth
    private lateinit var refUsers: DatabaseReference
    private var firebaseUserId: String = ""
    var databaseSpinTextCountry = ""
    var databaseSpinTextPref = ""

    /**
     *
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        mAuth = FirebaseAuth.getInstance()

        //
        val topToolbar = findViewById<Toolbar>(R.id.top_toolbar_search)
        setSupportActionBar(topToolbar)
        supportActionBar?.title = getString(R.string.settings)
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val spinnerCountry: Spinner = findViewById(R.id.spnCountry)
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.countries,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice)
            // Apply the adapter to the spinner
            spinnerCountry.adapter = adapter
        }
        with(spinnerCountry) {
            prompt = getString(R.string.select_country)
        }

        val spinnerPref: Spinner = findViewById(R.id.spnIntrests)
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.preferences,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice)
            // Apply the adapter to the spinner
            spinnerPref.adapter = adapter
        }
        with(spinnerPref) {
            prompt = getString(R.string.preferences)
        }

        spnCountry.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
//                users = parent?.getItemAtPosition(position) as Users
                val spinText = spinnerCountry.selectedItem.toString()
                println(spinText)
                firebaseUserId = mAuth.currentUser!!.uid
                refUsers =
                    FirebaseDatabase.getInstance().reference.child("Users").child(firebaseUserId)

                if (spinText == "Australia") {
                    databaseSpinTextCountry = "au"
                } else if (spinText == "United Kingdom") {
                    databaseSpinTextCountry = "gb"
                } else if (spinText == "United States of America") {
                    databaseSpinTextCountry = "us"
                }
            }
        }
        spnIntrests.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
//                users = parent?.getItemAtPosition(position) as Users
                val spinText = spinnerPref.selectedItem.toString()
                println(spinText)
                firebaseUserId = mAuth.currentUser!!.uid
                refUsers =
                    FirebaseDatabase.getInstance().reference.child("Users").child(firebaseUserId)

                if (spinText == "Business") {
                    databaseSpinTextPref = "business"
                } else if (spinText == "Entertainment") {
                    databaseSpinTextPref = "entertainment"
                } else if (spinText == "General") {
                    databaseSpinTextPref = "general"
                } else if (spinText == "Science") {
                    databaseSpinTextPref = "science"
                } else if (spinText == "Sports") {
                    databaseSpinTextPref = "sports"

                }
                println(spinText)
            }

        }
        settingsUpdateBtn.setOnClickListener {
            val userHashMap = HashMap<String, Any>()

            userHashMap["country"] = databaseSpinTextCountry
            userHashMap["preferences"] = databaseSpinTextPref
            refUsers.updateChildren(userHashMap)

            Toast.makeText(this@Settings, "Updated", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@Settings, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
            finish()
        }
    }

    /**
     *
     */
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.top_toolbar_search, menu)
        return super.onCreateOptionsMenu(menu)
    }
}