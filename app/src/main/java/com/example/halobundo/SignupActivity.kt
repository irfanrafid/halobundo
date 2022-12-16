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


class SignupActivity : AppCompatActivity() {

    lateinit var bSignUp : Button
    lateinit var sPassword : EditText
    lateinit var sNama : EditText
    lateinit var sUsername : EditText
    lateinit var sEmail : EditText
    lateinit var sPhoneNumber : EditText

    lateinit var mDatabaseReference: DatabaseReference
    lateinit var mFirebaseInstance : FirebaseDatabase
    lateinit var mDatabase: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        mFirebaseInstance = FirebaseDatabase.getInstance()
        mDatabase = FirebaseDatabase.getInstance().getReference()
        mDatabaseReference = mFirebaseInstance.getReference("User")

        bSignUp = findViewById<Button>(R.id.BSignUp)
        sPassword = findViewById<EditText>(R.id.Signpassword)
        sNama = findViewById<EditText>(R.id.Signnama)
        sEmail = findViewById<EditText>(R.id.Signemail)
        sPhoneNumber = findViewById<EditText>(R.id.Signphone)

        bSignUp.setOnClickListener {
            val passwordSign = sPassword.text.toString().trim()
            val namaSign = sNama.text.toString().trim()
            val emailSign = sEmail.text.toString().trim()
            val phoneSign = sPhoneNumber.text.toString().trim()

             if(namaSign.isEmpty()){
                sNama.error = "Silahkan isi nama anda"
                sNama.requestFocus()
                return@setOnClickListener

            }else if(emailSign.isEmpty()){
                sEmail.error = "Silahkan isi email anda"
                sEmail.requestFocus()
                return@setOnClickListener
            }else if (passwordSign.isEmpty()){
                sPassword.error = "Silahkan isi password anda"
                sPassword.requestFocus()
                return@setOnClickListener

            }else if (phoneSign.isEmpty()) {
                 sPhoneNumber.error = "Silahkan isi nomor hp anda"
                 sPhoneNumber.requestFocus()
                 return@setOnClickListener
             }else  {
                saveUsername(passwordSign,namaSign,emailSign, phoneSign)
            }
        }
    }

    private fun saveUsername(passwordSign: String, namaSign: String, emailSign: String, phoneNumber: String) {
        var user = User()
        user.email = emailSign
        user.name = namaSign
        user.password = passwordSign
        user.mobileNumber = phoneNumber

        if (namaSign != null) {
            checkingUsername(phoneNumber, user)
        }
    }

    private fun checkingUsername(mobileNumber: String, data: User) {
        mDatabaseReference.child(mobileNumber).addValueEventListener(object : ValueEventListener{
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var user = dataSnapshot.getValue(User::class.java)
                if (user == null){
                    mDatabaseReference.child(mobileNumber).setValue(data)

                    Toast.makeText(
                        applicationContext, "yeay, akunmu berhasil terdaftar",
                        Toast.LENGTH_LONG).show()

                    var intent = Intent(this@SignupActivity, SigninActivity::class.java)
                    startActivity(intent)

                } else {
                    Toast.makeText(
                        applicationContext, "No Hp sudah digunakan",
                        Toast.LENGTH_LONG).show()
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