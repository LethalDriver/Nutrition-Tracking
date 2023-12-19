package com.mwdziak.fitness_mobile_client.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import com.mwdziak.fitness_mobile_client.R
import com.mwdziak.fitness_mobile_client.databinding.ActivityLoginBinding
import com.mwdziak.fitness_mobile_client.databinding.ActivityRegisterBinding
import com.mwdziak.fitness_mobile_client.viewmodel.LoginViewModel
import com.mwdziak.fitness_mobile_client.viewmodel.RegisterViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private val viewModel: RegisterViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val loginLink = findViewById<TextView>(R.id.loginLink)
        loginLink.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.editTextFirstName.addTextChangedListener { text ->
            viewModel.updateFirstName(text.toString())
        }

        binding.editTextLastName.addTextChangedListener { text ->
            viewModel.updateLastName(text.toString())
        }

        binding.editTextEmail.addTextChangedListener { text ->
            viewModel.updateEmail(text.toString())
        }

        binding.editTextPassword.addTextChangedListener { text ->
            viewModel.updatePassword(text.toString())
        }

        binding.registerButton.setOnClickListener {
            lifecycleScope.launch {
                val authenticationResponse = viewModel.register()
                val intent = Intent(this@RegisterActivity, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}