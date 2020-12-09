package com.example.newsaggregator

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.newsaggregator.MainActivity
import com.example.newsaggregator.R
import com.example.newsaggregator.RegisterActivity
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login_screen.*
import kotlinx.android.synthetic.main.activity_welcome_screen.*

/**
 *
 */
class LogInActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth

    /**
     *
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_screen)

        mAuth = FirebaseAuth.getInstance()

        //
        logInBtn.setOnClickListener {
            logInUser()
        }

        //
        createAccountBtn.setOnClickListener {
            val intent = Intent(this@LogInActivity, RegisterActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
            finish()
        }
    }

    /**
     *
     */
    private fun logInUser() {
        val emailAddress: String = emailLogIn.text.toString()
        val password: String = passwordLogIn.text.toString()

        if (emailAddress.equals("")){
            Toast.makeText(this@LogInActivity, "Email Address field is empty", Toast.LENGTH_LONG).show()
        }
        else if (password.equals("")){
            Toast.makeText(this@LogInActivity, "Password field is empty", Toast.LENGTH_LONG).show()
        }
        else {
            mAuth.signInWithEmailAndPassword(emailAddress, password) .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val intent = Intent(this@LogInActivity, MainActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NO_ANIMATION)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this@LogInActivity, "Error Message: " + task.exception!!.message.toString(), Toast.LENGTH_LONG).show()
                }
            }
        }
    }


}