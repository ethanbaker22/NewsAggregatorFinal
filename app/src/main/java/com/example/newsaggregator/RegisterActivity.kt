package com.example.newsaggregator

import android.content.Intent
import android.os.Bundle
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_register_screen.*

/**
 *
 */
class RegisterActivity : AppCompatActivity() {

    //
    private lateinit var mAuth: FirebaseAuth
    private lateinit var refUsers: DatabaseReference
    private var firebaseUserId: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_screen)

        mAuth = FirebaseAuth.getInstance()

        //
        registerBtn.setOnClickListener {
            registerUser()
        }

//        val spinner: Spinner = findViewById(R.id.spinner)
//        // Create an ArrayAdapter using the string array and a default spinner layout
//        ArrayAdapter.createFromResource(
//            this,
//            R.array.countries,
//            android.R.layout.simple_spinner_item
//        ).also { adapter ->
//            // Specify the layout to use when the list of choices appears
//            adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice)
//            // Apply the adapter to the spinner
//            spinner.adapter = adapter
//        }
    }

    /**
     *
     */
    private fun registerUser() {
        val emailAddress: String = emailRegister.text.toString()
        val password: String = passwordRegister.text.toString()
        val firstName: String = firstNameRegister.text.toString()
        val lastName: String = lastNameRegister.text.toString()
        val country: String = "gb"

        //
        if (emailAddress.equals("")){
            Toast.makeText(this@RegisterActivity, "Email Address field is empty", Toast.LENGTH_LONG).show()
        } else if (password.equals("")){
            Toast.makeText(this@RegisterActivity, "Password field is empty", Toast.LENGTH_LONG).show()
        } else if (firstName.equals("")){
            Toast.makeText(this@RegisterActivity, "First Name field is empty", Toast.LENGTH_LONG).show()
        } else if (lastName.equals("")){
            Toast.makeText(this@RegisterActivity, "Last Name field is empty", Toast.LENGTH_LONG).show()
        } else {
            mAuth.createUserWithEmailAndPassword(emailAddress, password).addOnCompleteListener{ task ->
                if (task.isSuccessful){
                    firebaseUserId = mAuth.currentUser!!.uid
                    refUsers = FirebaseDatabase.getInstance().reference.child("Users").child(firebaseUserId)

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
                            finish()
                        }
                    }
                } else {
                    Toast.makeText(this@RegisterActivity, "Error Message: " + task.exception!!.message.toString(), Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}

//class SpinnerActivity : Activity(), AdapterView.OnItemSelectedListener {
//
//    override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
//        // An item was selected. You can retrieve the selected item using
//        // parent.getItemAtPosition(pos)
//    }
//
//    override fun onNothingSelected(parent: AdapterView<*>) {
//        // Another interface callback
//    }
//}
