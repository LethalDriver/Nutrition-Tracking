package com.mwdziak.fitness_mobile_client.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.mwdziak.fitness_mobile_client.R
import com.mwdziak.fitness_mobile_client.databinding.FragmentLoginBinding
import com.mwdziak.fitness_mobile_client.databinding.FragmentMainDashboardBinding
import com.mwdziak.fitness_mobile_client.viewmodel.LoginViewModel
import com.mwdziak.fitness_mobile_client.viewmodel.MainDashboardViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainDashboardFragment : Fragment() {
    private val viewModel: MainDashboardViewModel by viewModel()
    private var _binding: FragmentMainDashboardBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = MainDashboardFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.addMealButton.setOnClickListener {
            findNavController().navigate(R.id.action_mainDashboardFragment_to_addMealFragment)
        }
    }

}