package com.example.halobundo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.halobundo.model.User
import com.example.halobundo.util.Preferences
import com.google.firebase.database.*

class SigninActivity : AppCompatActivity() {

    lateinit var bDaftar: Button
    lateinit var etEmail: EditText
    lateinit var etPassword: EditText
    lateinit var bLogin: Button

    lateinit var mDatabaseReference: DatabaseReference
    lateinit var preference: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)

        mDatabaseReference = FirebaseDatabase.getInstance().getReference("User")
        preference = Preferences(this)

        bDaftar = findViewById(R.id.bDaftar)
        etEmail = findViewById<EditText>(R.id.etEmail)
        etPassword = findViewById<EditText>(R.id.etPassword)
        bLogin = findViewById<Button>(R.id.bLogin)

        bDaftar.setOnClickListener{
            var gotoDaftar = Intent(this,SignupActivity::class.java )
            startActivity(gotoDaftar)
        }

        bLogin.setOnClickListener {
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()


            if(email.isEmpty()){
                etEmail.error = "email wajib diisikan"
                etEmail.requestFocus()
                return@setOnClickListener
            } else if(password.isEmpty()){
                etPassword.error = "password wajib diisikan"
                etPassword.requestFocus()
                return@setOnClickListener
            }else{
                pushLogin(email, password)
            }
        }

    }

    private fun pushLogin(username: String, password: String) {
        mDatabaseReference.child(username).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var user = dataSnapshot.getValue(User::class.java)
                if (user == null) {
                    Toast.makeText(
                        applicationContext, "User tidak ditemukan",
                        Toast.LENGTH_LONG).show()
                } else {

                    if (user.password.equals(password)) {

                        preference.setValues("nama", user.name.toString())
                        preference.setValues("email", user.email.toString())
                        preference.setValues("status", "1")




                        finishAffinity()
                        var intent = Intent(this@SigninActivity, HomeActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(
                            applicationContext, "Password anda salah",
                            Toast.LENGTH_LONG).show()
                    }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(
                    applicationContext, databaseError.message,
                    Toast.LENGTH_LONG).show()
            }
        })
    }

}