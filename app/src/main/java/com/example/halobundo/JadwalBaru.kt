package com.example.halobundo

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import android.widget.TextView
import android.widget.TimePicker
import java.util.*

class JadwalBaru : AppCompatActivity(), DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    var jam = 0
    var menit = 0
    var tanggal = 0
    var bulan = 0
    var tahun = 0
    lateinit var teksTanggal: TextView
    lateinit var teksWaktu: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun getSaatIni(){
        val kal: Calendar = Calendar.getInstance()
        tanggal = kal.get(Calendar.DAY_OF_MONTH)
        bulan = kal.get(Calendar.MONTH)
        tahun = kal.get(Calendar.YEAR)
        jam = kal.get(Calendar.HOUR_OF_DAY)
        menit = kal.get(Calendar.MINUTE)
    }

    fun fSetTanggal(view: View){
        getSaatIni()
        DatePickerDialog(this, this,  tahun, bulan, tanggal).show()
    }

    fun fSetWaktu(view: View){
        getSaatIni()
        TimePickerDialog(this, this, jam, menit, true).show()
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        tanggal = dayOfMonth
        bulan = month
        tahun = year

        teksTanggal = findViewById(R.id.tTanggal)
        teksTanggal.text = "${tanggal} - ${bulan} - ${tahun}"
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        jam = hourOfDay
        menit = minute

        teksWaktu = findViewById(R.id.tWaktu)
        teksWaktu.text = "${jam}:${menit}"
    }
}