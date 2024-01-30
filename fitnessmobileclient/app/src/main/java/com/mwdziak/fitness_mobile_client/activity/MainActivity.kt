package com.mwdziak.fitness_mobile_client.activity

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import androidx.annotation.RequiresApi
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.navigation.NavigationView
import com.mwdziak.fitness_mobile_client.R
import com.mwdziak.fitness_mobile_client.auth.LogoutRequest
import com.mwdziak.fitness_mobile_client.service.HttpService
import com.mwdziak.fitness_mobile_client.service.TokenManager
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MainActivity() : AppCompatActivity() {
    private val tokenManager: TokenManager by inject()
    private val httpService: HttpService by inject()

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
                    drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }
                R.id.nav_main_dashboard -> {
                    findNavController(R.id.nav_host_fragment).navigate(R.id.mainDashboardFragment)
                    drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }
                R.id.nav_logout -> {
                    val logoutRequest = LogoutRequest(tokenManager.getJwtToken()!!,
                        tokenManager.getRefreshToken()!!)
                    tokenManager.deleteTokens()
                    val intent = Intent(this@MainActivity, StartupActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)

                    true
                }
                else -> false
            }
        }
    }
}