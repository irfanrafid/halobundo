package com.example.halobundo

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

object realTimeDataBase {
    fun instance() = Firebase.database
}