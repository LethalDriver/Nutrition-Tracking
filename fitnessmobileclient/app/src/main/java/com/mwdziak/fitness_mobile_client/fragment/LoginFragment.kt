package com.mwdziak.fitness_mobile_client.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import com.mwdziak.fitness_mobile_client.R
import com.mwdziak.fitness_mobile_client.activity.MainActivity
import com.mwdziak.fitness_mobile_client.databinding.FragmentLoginBinding
import com.mwdziak.fitness_mobile_client.utils.showSnackBar
import com.mwdziak.fitness_mobile_client.viewmodel.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {
    private val viewModel: LoginViewModel by viewModel()
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        if (viewModel.isUserLoggedIn()) {
            val intent = Intent(requireContext(), MainActivity::class.java)
            intent.putExtra("FROM_ACTIVITY", "StartupActivity")
            startActivity(intent)
        }

        binding.registerLink.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        binding.editTextEmail.addTextChangedListener { text ->
            viewModel.updateEmail(text.toString())
        }

        binding.editTextPassword.addTextChangedListener { text ->
            viewModel.updatePassword(text.toString())
        }

        binding.loginButton.setOnClickListener {
            viewModel.authenticate()
        }

        viewModel.authenticationState.observe(viewLifecycleOwner) { authenticationState ->
            when (authenticationState) {
                LoginViewModel.AuthenticationState.LOADING -> {
                    binding.loginButton.isEnabled = false
                }

                LoginViewModel.AuthenticationState.AUTHENTICATED -> {
                    binding.loginButton.isEnabled = true
                    val intent = Intent(requireContext(), MainActivity::class.java)
                    startActivity(intent)
                }

                LoginViewModel.AuthenticationState.FAILED -> {
                    binding.loginButton.isEnabled = true
                    showSnackBar(viewModel.exceptionMessage.value ?: "", true)
                }
                null -> {
                    binding.loginButton.isEnabled = true
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    companion object {
        fun newInstance() = LoginFragment()
    }
}