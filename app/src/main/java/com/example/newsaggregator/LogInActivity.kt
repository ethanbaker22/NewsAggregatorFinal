package com.example.newsaggregator

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login_screen.*

/**
 * @author Ethan Baker - 986237
 * @class LogInActivity.kt
 * @version 1.0
 * Activity Class for Login
 */
class LogInActivity : AppCompatActivity() {

    // Firebase Auth used in Logging in
    private lateinit var mAuth: FirebaseAuth

    /**
     * When this activity is created
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_screen)

        mAuth = FirebaseAuth.getInstance()

        //
        logInBtn.setOnClickListener {
            logInUser()
        }

        // Allows user to click create account from login screen
        createAccountBtn.setOnClickListener {
            val intent = Intent(this@LogInActivity, RegisterActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
            finish()
        }
    }

    /**
     * Logs in user if credentials metch the Firebase Realtime Database
     */
    private fun logInUser() {
        val emailAddress: String = emailLogIn.text.toString()
        val password: String = passwordLogIn.text.toString()

        // If no info is supplied
        if (emailAddress.equals("")){
            Toast.makeText(this@LogInActivity, "Email Address field is empty", Toast.LENGTH_LONG).show()
        }
        else if (password.equals("")){
            Toast.makeText(this@LogInActivity, "Password field is empty", Toast.LENGTH_LONG).show()
        }
        // If info is inputted
        else {
            mAuth.signInWithEmailAndPassword(emailAddress, password) .addOnCompleteListener { task ->
                // Check if details are correct
                if (task.isSuccessful) {
                    val intent = Intent(this@LogInActivity, MainActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NO_ANIMATION)
                    startActivity(intent)
                    finish() // Doesn't allow user to mess with the program using back arrow
                } else {
                    // Error message
                    Toast.makeText(this@LogInActivity, "Error Message: " + task.exception!!.message.toString(), Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}