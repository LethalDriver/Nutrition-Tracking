package com.mwdziak.fitness_mobile_client

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import androidx.annotation.RequiresApi
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val topAppBar = findViewById<MaterialToolbar>(R.id.topAppBar)
        val drawerLayout = findViewById<DrawerLayout>(R.id.drawerLayout)
        val navigationView = findViewById<NavigationView>(R.id.navigationView)
        val hydrationBar = findViewById<ProgressBar>(R.id.hydrationBar)
        val caloriesBar = findViewById<ProgressBar>(R.id.caloriesBar)
        val proteinBar = findViewById<ProgressBar>(R.id.proteinBar)
        val carbsBar = findViewById<ProgressBar>(R.id.carbohydratesBar)

        hydrationBar.min = 0
        hydrationBar.max = 100
        hydrationBar.progress = 50

        topAppBar.setNavigationOnClickListener {
            drawerLayout.open()
        }

        navigationView.setNavigationItemSelectedListener { menuItem ->
            // Handle menu item selected
            menuItem.isChecked = true
            drawerLayout.close()
            true
        }
    }
}