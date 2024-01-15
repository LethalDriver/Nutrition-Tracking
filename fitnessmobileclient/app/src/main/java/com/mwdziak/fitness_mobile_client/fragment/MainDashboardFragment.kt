package com.mwdziak.fitness_mobile_client.fragment

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.os.Build
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import android.widget.ProgressBar
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.google.android.material.snackbar.Snackbar
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

    @RequiresApi(Build.VERSION_CODES.O)
    private fun updateProgressBars() {
        binding.caloriesBar.min = 0
        binding.caloriesBar.max = viewModel.getCaloriesGoal().toInt()
        binding.proteinBar.min = 0
        binding.proteinBar.max = viewModel.getProteinGoal().toInt()
        binding.carbohydratesBar.min = 0
        binding.carbohydratesBar.max = viewModel.getCarbohydratesGoal().toInt()
        binding.fatBar.min = 0
        binding.fatBar.max = viewModel.getFatGoal().toInt()

    }

    private fun animateProgressBar(progressBar: CircularProgressIndicator, newValue: Int) {

        val currentValue = progressBar.progress

        // Create an ObjectAnimator
        val animator = ObjectAnimator.ofInt(progressBar, "progress", currentValue, newValue)

        // Set the duration of the animation
        animator.duration = 2000 // 2 seconds

        // Set the animation interpolator
        animator.interpolator = LinearInterpolator()

        // Set the animation repeat count and mode
        animator.repeatCount = ValueAnimator.INFINITE
        animator.repeatMode = ValueAnimator.RESTART

        // Start the animation
        animator.start()
    }



}