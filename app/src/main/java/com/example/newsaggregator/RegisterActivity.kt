package com.example.newsaggregator

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_register_screen.*

/**
 * @author Ethan Baker - 986237
 * @class RegisterActivity.kt
 * @version 1.2
 * Activity Class for Register Activity
 */
class RegisterActivity : AppCompatActivity() {

    // Firebase Auth used in Registering
    private lateinit var mAuth: FirebaseAuth
    private lateinit var refUsers: DatabaseReference
    private var firebaseUserId: String = ""

    /**
     * When this activity is created
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_screen)

        mAuth = FirebaseAuth.getInstance()

        //
        registerBtn.setOnClickListener {
            registerUser()
        }
    }

    /**
     * Method in which user's are asked to input their data, this will be checked for the following:
     * If field is populated, Correctness, Format, Password needs to be 6 characters.
     */
    private fun registerUser() {
        val emailAddress: String = emailRegister.text.toString()
        val password: String = passwordRegister.text.toString()
        val firstName: String = firstNameRegister.text.toString()
        val lastName: String = lastNameRegister.text.toString()
        val country: String = "gb" // set default country to the UK (gb) in the database

        // Check that there is actually data
        if (emailAddress.equals("")){
            Toast.makeText(this@RegisterActivity, "Email Address field is empty", Toast.LENGTH_LONG).show()
        } else if (password.equals("")){
            Toast.makeText(this@RegisterActivity, "Password field is empty", Toast.LENGTH_LONG).show()
        } else if (firstName.equals("")){
            Toast.makeText(this@RegisterActivity, "First Name field is empty", Toast.LENGTH_LONG).show()
        } else if (lastName.equals("")){
            Toast.makeText(this@RegisterActivity, "Last Name field is empty", Toast.LENGTH_LONG).show()
        } else {
            // If info is inputted
            mAuth.createUserWithEmailAndPassword(emailAddress, password).addOnCompleteListener{ task ->
                if (task.isSuccessful){
                    firebaseUserId = mAuth.currentUser!!.uid
                    refUsers = FirebaseDatabase.getInstance().reference.child("Users").child(firebaseUserId)

                    // Passes data to Firebase
                    val userHashMap = HashMap<String, Any>()
                    userHashMap["uid"] = firebaseUserId
                    userHashMap["emailAddress"] = emailAddress
                    userHashMap["firstName"] = firstName
                    userHashMap["lastName"] = lastName
                    userHashMap["country"] = country

                    refUsers.updateChildren(userHashMap).addOnCompleteListener { task ->  
                        if (task.isSuccessful){
                            val intent = Intent(this@RegisterActivity, MainActivity::class.java)
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NO_ANIMATION)
                            startActivity(intent)
                            finish() // Doesn't allow user to mess with the program using back arrow
                        }
                    }
                } else {
                    Toast.makeText(this@RegisterActivity, "Error Message: " + task.exception!!.message.toString(), Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}