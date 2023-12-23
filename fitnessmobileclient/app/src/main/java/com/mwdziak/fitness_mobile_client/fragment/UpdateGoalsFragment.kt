package com.mwdziak.fitness_mobile_client.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import com.mwdziak.fitness_mobile_client.R
import com.mwdziak.fitness_mobile_client.activity.MainActivity
import com.mwdziak.fitness_mobile_client.databinding.FragmentUpdateGoalsBinding
import com.mwdziak.fitness_mobile_client.viewmodel.UpdateGoalsViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class UpdateGoalsFragment : Fragment() {
    private val viewModel: UpdateGoalsViewModel by viewModel()
    private var _binding: FragmentUpdateGoalsBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = UpdateGoalsFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUpdateGoalsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.editTextCalories.addTextChangedListener{ text ->
            viewModel.updateCalories(text.toString())
        }
        binding.editTextProtein.addTextChangedListener{ text ->
            viewModel.updateProtein(text.toString())
        }
        binding.editTextCarbohydrates.addTextChangedListener{ text ->
            viewModel.updateCarbohydrates(text.toString())
        }
        binding.editTextFat.addTextChangedListener{ text ->
            viewModel.updateFat(text.toString())
        }
        binding.saveButton.setOnClickListener {
            lifecycleScope.launch {
                viewModel.updateGoals()
            }
            val intent = Intent(requireContext(), MainActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}