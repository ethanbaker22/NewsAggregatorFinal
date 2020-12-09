package com.example.newsaggregator

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.newsaggregator.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_register_screen.*
import kotlinx.android.synthetic.main.activity_welcome_screen.*

/**
 * @author Ethan Baker - 986237
 * @class WelcomeActivity.kt
 * @version 1.0
 * Activity Class for the Welcome Screen
 */
class WelcomeActivity : AppCompatActivity(){

    /**
     * When this activity is created
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome_screen)

        // If login is pressed
        logInWelcomeBtn.setOnClickListener {
            val intent = Intent(this@WelcomeActivity, LogInActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT)
            startActivity(intent)
        }

        // If create account is pressed
        createAccountWelcomeBtn.setOnClickListener {
            val intent = Intent(this@WelcomeActivity, RegisterActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT)
            startActivity(intent)
        }
    }
}