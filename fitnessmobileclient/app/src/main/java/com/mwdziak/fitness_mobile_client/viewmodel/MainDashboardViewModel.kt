package com.mwdziak.fitness_mobile_client.viewmodel

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mwdziak.fitness_mobile_client.service.HttpService
import kotlinx.coroutines.launch

class MainDashboardViewModel(private val httpService: HttpService,
    private val sharedPreferences: SharedPreferences) : ViewModel() {
    private val caloriesProgress = MutableLiveData<Double>(0.0)
    private val proteinProgress = MutableLiveData<Double>(0.0)
    private val carbohydratesProgress = MutableLiveData<Double>(0.0)
    private val fatProgress = MutableLiveData<Double>(0.0)
    private val caloriesGoal = MutableLiveData<Double>(0.0)
    private val proteinGoal = MutableLiveData<Double>(0.0)
    private val carbohydratesGoal = MutableLiveData<Double>(0.0)
    private val fatGoal = MutableLiveData<Double>(0.0)
    fun updateData() {
        getProgressFromSharedPreferences()
        viewModelScope.launch {
            val fetch = viewModelScope.launch {
                getGoals()
                getProgress()
            }
            fetch.join()
            saveProgressToSharedPreferences()
        }
    }

    private suspend fun getGoals() {
        val goals = httpService.getGoals()
        if (goals.calories != caloriesGoal.value) {
            caloriesGoal.value = goals.calories ?: 0.0
        }
        if (goals.protein != proteinGoal.value) {
            proteinGoal.value = goals.protein ?: 0.0
        }
        if (goals.carbohydrates != carbohydratesGoal.value) {
            carbohydratesGoal.value = goals.carbohydrates ?: 0.0
        }
        if (goals.fat != fatGoal.value) {
            fatGoal.value = goals.fat ?: 0.0
        }
    }

    private suspend fun getProgress() {
        val progress = httpService.getProgress()
        if (progress.calories != caloriesProgress.value) {
            caloriesProgress.value = progress.calories ?: 0.0
        }
        if (progress.protein != proteinProgress.value) {
            proteinProgress.value = progress.protein ?: 0.0
        }
        if (progress.carbohydrates != carbohydratesProgress.value) {
            carbohydratesProgress.value = progress.carbohydrates ?: 0.0
        }
        if (progress.fat != fatProgress.value) {
            fatProgress.value = progress.fat ?: 0.0
        }
    }

    fun getCaloriesProgress(): LiveData<Double> {
        return caloriesProgress
    }
    fun getProteinProgress(): LiveData<Double> {
        return proteinProgress
    }
    fun getCarbohydratesProgress(): LiveData<Double> {
        return carbohydratesProgress
    }
    fun getFatProgress(): LiveData<Double> {
        return fatProgress
    }

    fun getCaloriesGoal(): LiveData<Double> {
        return caloriesGoal
    }
    fun getProteinGoal(): LiveData<Double> {
        return proteinGoal
    }
    fun getCarbohydratesGoal(): LiveData<Double> {
        return carbohydratesGoal
    }
    fun getFatGoal(): LiveData<Double> {
        return fatGoal
    }

    private fun saveProgressToSharedPreferences() {
        val editor = sharedPreferences.edit()
        editor.putFloat("CALORIES_PROGRESS", caloriesProgress.value?.toFloat() ?: 0.0f)
        editor.putFloat("PROTEIN_PROGRESS", proteinProgress.value?.toFloat() ?: 0.0f)
        editor.putFloat("CARBOHYDRATES_PROGRESS", carbohydratesProgress.value?.toFloat() ?: 0.0f)
        editor.putFloat("FAT_PROGRESS", fatProgress.value?.toFloat() ?: 0.0f)
        editor.apply()
    }
    
    private fun getProgressFromSharedPreferences() {
        caloriesProgress.value = sharedPreferences.getFloat("CALORIES_PROGRESS", 0.0f).toDouble()
        proteinProgress.value = sharedPreferences.getFloat("PROTEIN_PROGRESS", 0.0f).toDouble()
        carbohydratesProgress.value = sharedPreferences.getFloat("CARBOHYDRATES_PROGRESS", 0.0f).toDouble()
        fatProgress.value = sharedPreferences.getFloat("FAT_PROGRESS", 0.0f).toDouble()
    }
}