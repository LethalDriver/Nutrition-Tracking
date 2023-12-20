package com.mwdziak.fitness_mobile_client.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import com.mwdziak.fitness_mobile_client.R
import com.mwdziak.fitness_mobile_client.databinding.ActivityRegisterBinding
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

        binding.loginLink.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.editTextFirstName.addTextChangedListener { text ->
            viewModel.updateFirstName(text.toString())
            if (!viewModel.isFirstNameValid()) {
                binding.editTextFirstName.error = "First name cannot be blank"
            }
        }

        binding.editTextLastName.addTextChangedListener { text ->
            viewModel.updateLastName(text.toString())
            if (!viewModel.isLastNameValid()) {
                binding.editTextLastName.error = "Last name cannot be blank"
            }
        }

        binding.editTextEmail.addTextChangedListener { text ->
            viewModel.updateEmail(text.toString())
            if (!viewModel.isEmailValid()) {
                binding.editTextEmail.error = "Invalid email"
            }
        }

        binding.editTextPassword.addTextChangedListener { text ->
            viewModel.updatePassword(text.toString())
            if (!viewModel.isPasswordValid()) {
                binding.editTextPassword.error = "Password must be at least 8 characters long," +
                        "contain a number, and a special character"
            }
        }

        binding.editTextConfirmPassword.addTextChangedListener { text ->
            viewModel.updateConfirmPassword(text.toString())
            if (!viewModel.isConfirmPasswordValid()) {
                binding.editTextConfirmPassword.error = "Passwords do not match"
            }
        }

        viewModel.isAllFieldsValid.observe(this) { isValid ->
            binding.registerButton.isEnabled = isValid
        }

        binding.registerButton.setOnClickListener {
            lifecycleScope.launch {
                viewModel.register()
            }
            val intent = Intent(this@RegisterActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}