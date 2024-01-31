package com.mwdziak.fitness_mobile_client.fragment

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.os.Build
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import android.widget.ProgressBar
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.google.android.material.snackbar.Snackbar
import com.mwdziak.fitness_mobile_client.R
import com.mwdziak.fitness_mobile_client.databinding.FragmentLoginBinding
import com.mwdziak.fitness_mobile_client.databinding.FragmentMainDashboardBinding
import com.mwdziak.fitness_mobile_client.viewmodel.LoginViewModel
import com.mwdziak.fitness_mobile_client.viewmodel.MainDashboardViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
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
        val topAppBar = activity?.findViewById<MaterialToolbar>(R.id.topAppBar)
        topAppBar?.title = "Main Dashboard"

        Log.w("MainDashboardFragment", "onViewCreated: ${viewModel.getCaloriesProgress().value}")

        initializeBars()
        observeViewModel()
        viewModel.updateData()

        binding.addMealButton.setOnClickListener {
            findNavController().navigate(R.id.action_mainDashboardFragment_to_addMealFragment)
            Log.w("MainDashboardFragment", "onNavAway: ${viewModel.getCaloriesProgress().value}")
        }

    }

    private fun initializeBars() {
        binding.caloriesBar.max = (viewModel.getCaloriesGoal().value?.toInt()?.times(1000)) ?: 0
        binding.proteinBar.max = (viewModel.getProteinGoal().value?.toInt()?.times(1000)) ?: 0
        binding.carbohydratesBar.max = (viewModel.getCarbohydratesGoal().value?.toInt()?.times(1000)) ?: 0
        binding.fatBar.max = (viewModel.getFatGoal().value?.toInt()?.times(1000)) ?: 0
        binding.caloriesBar.progress = (viewModel.getCaloriesProgress().value?.toInt()?.times(1000)) ?: 0
        binding.proteinBar.progress = (viewModel.getProteinProgress().value?.toInt()?.times(1000)) ?: 0
        binding.carbohydratesBar.progress = (viewModel.getCarbohydratesProgress().value?.toInt()?.times(1000)) ?: 0
        binding.fatBar.progress = (viewModel.getFatProgress().value?.toInt()?.times(1000)) ?: 0
    }

    @SuppressLint("SetTextI18n")
    private fun observeViewModel() {
        viewModel.getCaloriesGoal().observe(viewLifecycleOwner) { newValue: Double ->
            binding.caloriesBar.max = newValue.toInt() * 1000
        }
        viewModel.getProteinGoal().observe(viewLifecycleOwner) { newValue: Double ->
            binding.proteinBar.max = newValue.toInt() * 1000
        }
        viewModel.getCarbohydratesGoal().observe(viewLifecycleOwner) { newValue: Double ->
            binding.carbohydratesBar.max = newValue.toInt() * 1000
        }
        viewModel.getFatGoal().observe(viewLifecycleOwner) { newValue: Double ->
            binding.fatBar.max = newValue.toInt() * 1000
        }
        viewModel.getCaloriesProgress().observe(viewLifecycleOwner) { newValue: Double ->
            animateProgressBar(binding.caloriesBar, binding.caloriesBar.progress, newValue.toInt() * 1000)
            binding.caloriesTextView.text = "${newValue.toInt()}kJ"
        }
        viewModel.getProteinProgress().observe(viewLifecycleOwner) { newValue: Double ->
            animateProgressBar(binding.proteinBar, binding.proteinBar.progress, newValue.toInt() * 1000)
            binding.proteinTextView.text = "${newValue.toInt()}g"
        }
        viewModel.getCarbohydratesProgress().observe(viewLifecycleOwner) { newValue: Double ->
            animateProgressBar(
                binding.carbohydratesBar,
                binding.carbohydratesBar.progress,
                newValue.toInt() * 1000
            )
            binding.carbsTextView.text = "${newValue.toInt()}g"
        }
        viewModel.getFatProgress().observe(viewLifecycleOwner) { newValue: Double ->
            animateProgressBar(binding.fatBar, binding.fatBar.progress, newValue.toInt() * 1000)
            binding.fatTextView.text = "${newValue.toInt()}g"
        }
    }

    private fun animateProgressBar(progressBar: ProgressBar, oldValue: Int, newValue: Int) {
        val animator = ObjectAnimator.ofInt(progressBar, "progress", oldValue, newValue)
        animator.duration = 2000 // 2 seconds
        animator.interpolator = LinearInterpolator()
        animator.start()
    }



}