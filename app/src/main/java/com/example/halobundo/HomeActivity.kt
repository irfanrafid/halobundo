package com.example.halobundo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.halobundo.ui.menu.AppointmentFragment
import com.example.halobundo.ui.menu.ArticleFragment
import com.example.halobundo.ui.menu.ChatFragment
import com.example.halobundo.ui.menu.HomeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {

    lateinit var bottomNavigationView: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        val homeFragment = HomeFragment()


        setFragment(homeFragment)





    }

    private fun setFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransacion = fragmentManager.beginTransaction()
        fragmentTransacion.replace(R.id.layout_frame, fragment)
        fragmentTransacion.commit()
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.nav_home -> {
                val fragment = HomeFragment()
                setFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_periksa -> {
                val fragment = AppointmentFragment()
                setFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_artikel -> {
                val fragment = ArticleFragment()
                setFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_chat -> {
                val fragment = ChatFragment()
                setFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }
}