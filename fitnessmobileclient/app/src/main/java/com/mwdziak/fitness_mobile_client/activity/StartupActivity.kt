package com.mwdziak.fitness_mobile_client.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mwdziak.fitness_mobile_client.LoginFragment
import com.mwdziak.fitness_mobile_client.R

class StartupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_startup2)

        val loginFragment = LoginFragment.newInstance()

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, loginFragment)
            commit()
        }
    }
}