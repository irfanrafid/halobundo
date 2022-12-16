package com.example.halobundo.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
class Appointment (
    var id : String ?="",
    var mobileNumber: String ?= "",
    var place: String ?= "",
    var dateTime: String ?=""
        ):Parcelable