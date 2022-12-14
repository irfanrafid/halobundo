package com.example.halobundo.model


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class User (
    var id: String ?="",
    var name: String ?="",
    var email: String ?="",
    var password:String ?="",
    var mobileNumber: String ?=""
): Parcelable