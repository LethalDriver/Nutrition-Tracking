package com.mwdziak.fitness_mobile_client.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.mwdziak.fitness_mobile_client.R
import com.mwdziak.fitness_mobile_client.databinding.FragmentRegisterBinding
import com.mwdziak.fitness_mobile_client.viewmodel.RegisterViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterFragment : Fragment() {
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    private val viewModel: RegisterViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loginLink.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
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

        viewModel.isAllFieldsValid.observe(viewLifecycleOwner) { isValid ->
            binding.registerButton.isEnabled = isValid
        }

        binding.registerButton.setOnClickListener {
            lifecycleScope.launch {
                viewModel.register()
            }
            findNavController().navigate(R.id.action_registerFragment_to_updateGoalsFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}