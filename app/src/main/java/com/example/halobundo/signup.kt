package com.example.halobundo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.halobundo.model.User
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.*


class signup : AppCompatActivity() {

    lateinit var bSignUp : Button
    lateinit var sUsername : EditText
    lateinit var sPassword : EditText
    lateinit var sNama : EditText
    lateinit var sEmail : EditText

    lateinit var mDatabaseReference: DatabaseReference
    lateinit var mFirebaseInstance : FirebaseDatabase
    lateinit var mDatabase: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        mFirebaseInstance = FirebaseDatabase.getInstance()
        mDatabase = FirebaseDatabase.getInstance().getReference()
        mDatabaseReference = mFirebaseInstance.getReference("User")

        mFirebaseInstance = FirebaseDatabase.getInstance()
        mDatabase = FirebaseDatabase.getInstance().getReference()
        mDatabaseReference = mFirebaseInstance.getReference("User")

        bSignUp = findViewById<Button>(R.id.BSignUp)
        sUsername = findViewById<EditText>(R.id.Signusername)
        sPassword = findViewById<EditText>(R.id.Signpassword)
        sNama = findViewById<EditText>(R.id.Signnama)
        sEmail = findViewById<EditText>(R.id.Signemail)

        bSignUp.setOnClickListener {
            val usernameSign = sUsername.text.toString().trim()
            val passwordSign = sPassword.text.toString().trim()
            val namaSign = sNama.text.toString().trim()
            val emailSign = sEmail.text.toString().trim()

            if (usernameSign.isEmpty()){
                sUsername.error = "Silahkan isi username anda"
                sUsername.requestFocus()
                return@setOnClickListener
            }else if (passwordSign.isEmpty()){
                sPassword.error = "Silahkan isi password anda"
                sPassword.requestFocus()
                return@setOnClickListener
            }else if(namaSign.isEmpty()){
                sNama.error = "Silahkan isi nama anda"
                sNama.requestFocus()
                return@setOnClickListener
            }else if(emailSign.isEmpty()){
                sEmail.error = "Silahkan isi email anda"
                sEmail.requestFocus()
                return@setOnClickListener
            } else {
                saveUsername(usernameSign,passwordSign,namaSign,emailSign)
            }
        }
    }

    private fun saveUsername(usernameSign: String, passwordSign: String, namaSign: String, emailSign: String) {
        var user = User()
        user.email = emailSign
        user.username = usernameSign
        user.name = namaSign
        user.password = passwordSign

        if (usernameSign != null) {
            checkingUsername(usernameSign, user)
        }
    }

    private fun checkingUsername(usernameSign: String, data: User) {
        mDatabaseReference.child(usernameSign).addValueEventListener(object : ValueEventListener{
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var user = dataSnapshot.getValue(User::class.java)
                if (user == null){
                    mDatabaseReference.child(usernameSign).setValue(data)



                } else {

                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(
                    applicationContext, ""+databaseError.message,
                    Toast.LENGTH_LONG).show()
            }

        })
    }
}