package com.example.halobundo.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Article (
    var id : String ?="",
    var body: String ?= "",
    var judul: String ?= "",
    var kategori: String ?=""
): Parcelable