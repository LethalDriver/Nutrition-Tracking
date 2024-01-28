package com.mwdziak.fitness_mobile_client.activity

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import androidx.annotation.RequiresApi
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.navigation.NavigationView
import com.mwdziak.fitness_mobile_client.R

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val topAppBar = findViewById<MaterialToolbar>(R.id.topAppBar)
        val drawerLayout = findViewById<DrawerLayout>(R.id.drawerLayout)
        val navigationView = findViewById<NavigationView>(R.id.navigationView)

        topAppBar.setNavigationOnClickListener {
            drawerLayout.open()
        }

        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_history -> {
                    findNavController(R.id.nav_host_fragment).navigate(R.id.historyFragment)
                    true
                }
                R.id.anv_profile -> {
                    // Handle profile item click
                    true
                }
                R.id.nav_logout -> {
                    // Handle logout item click
                    true
                }
                else -> false
            }
        }
    }
}