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
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
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

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            val getGoalsJob = async { viewModel.getGoals() }
            val getProgressJob = async { viewModel.getProgress() }

            getGoalsJob.await()
            getProgressJob.await()

            viewModel.getProgressFromSharedPreferences()

            updateProgressBars()
            updateTextViews()
        }
        binding.addMealButton.setOnClickListener {
            viewModel.saveProgressToSharedPreferences()
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



        val navController = findNavController()
        val previousFragmentId = navController.previousBackStackEntry?.destination?.id

        if (previousFragmentId == R.id.addMealFragment) {
            animateProgressBar(binding.caloriesBar,
                viewModel.getPreviousProgressState("CALORIES_PROGRESS").toInt(), viewModel.getCaloriesProgress().toInt())
            animateProgressBar(binding.proteinBar,
                viewModel.getPreviousProgressState("PROTEIN_PROGRESS").toInt(), viewModel.getProteinProgress().toInt())
            animateProgressBar(binding.carbohydratesBar,
                viewModel.getPreviousProgressState("CARBOHYDRATES_PROGRESS").toInt(), viewModel.getCarbohydratesProgress().toInt())
            animateProgressBar(binding.fatBar,
                viewModel.getPreviousProgressState("FAT_PROGRESS").toInt(), viewModel.getFatProgress().toInt())
        } else {
            animateProgressBar(binding.caloriesBar, 0, viewModel.getCaloriesProgress().toInt())
            animateProgressBar(binding.proteinBar, 0, viewModel.getProteinProgress().toInt())
            animateProgressBar(binding.carbohydratesBar, 0, viewModel.getCarbohydratesProgress().toInt())
            animateProgressBar(binding.fatBar, 0, viewModel.getFatProgress().toInt())
        }
    }

    @SuppressLint("SetTextI18n")
    private fun updateTextViews() {
        binding.caloriesTextView.text = "${viewModel.getCaloriesProgress().toInt()}kJ"
        binding.proteinTextView.text = "${viewModel.getProteinProgress().toInt()}g"
        binding.carbsTextView.text = "${viewModel.getCarbohydratesProgress().toInt()}g"
        binding.fatTextView.text = "${viewModel.getFatProgress().toInt()}g"
    }

    private fun animateProgressBar(progressBar: ProgressBar, oldValue: Int, newValue: Int) {
        val animator = ObjectAnimator.ofInt(progressBar, "progress", oldValue, newValue)
        animator.duration = 2000 // 2 seconds
        animator.interpolator = LinearInterpolator()
        animator.start()
    }



}