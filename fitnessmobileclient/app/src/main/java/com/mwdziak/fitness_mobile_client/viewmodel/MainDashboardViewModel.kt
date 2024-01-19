package com.mwdziak.fitness_mobile_client.viewmodel

import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mwdziak.fitness_mobile_client.service.HttpService

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

    suspend fun getGoals() {
        val goals = httpService.getGoals()
        caloriesGoal.value = goals.calories ?: 0.0
        proteinGoal.value = goals.protein ?: 0.0
        carbohydratesGoal.value = goals.carbohydrates ?: 0.0
        fatGoal.value = goals.fat ?: 0.0
    }
    private suspend fun getProgress() {
        val progress = httpService.getProgress()
        caloriesProgress.value = progress.calories ?: 0.0
        proteinProgress.value = progress.protein ?: 0.0
        carbohydratesProgress.value = progress.carbohydrates ?: 0.0
        fatProgress.value = progress.fat ?: 0.0
    }

    fun getCaloriesProgress(): Double {
        return caloriesProgress.value ?: 0.0
    }
    fun getProteinProgress(): Double {
        return proteinProgress.value ?: 0.0
    }
    fun getCarbohydratesProgress(): Double {
        return carbohydratesProgress.value ?: 0.0
    }
    fun getFatProgress(): Double {
        return fatProgress.value ?: 0.0
    }

    fun getCaloriesGoal(): Double {
        return caloriesGoal.value ?: 0.0
    }
    fun getProteinGoal(): Double {
        return proteinGoal.value ?: 0.0
    }
    fun getCarbohydratesGoal(): Double {
        return carbohydratesGoal.value ?: 0.0
    }
    fun getFatGoal(): Double {
        return fatGoal.value ?: 0.0
    }

    fun saveProgressToSharedPreferences() {
        val editor = sharedPreferences.edit()
        editor.putFloat("CALORIES_PROGRESS", caloriesProgress.value?.toFloat() ?: 0.0f)
        editor.putFloat("PROTEIN_PROGRESS", proteinProgress.value?.toFloat() ?: 0.0f)
        editor.putFloat("CARBOHYDRATES_PROGRESS", carbohydratesProgress.value?.toFloat() ?: 0.0f)
        editor.putFloat("FAT_PROGRESS", fatProgress.value?.toFloat() ?: 0.0f)
        editor.apply()
    }
    
    fun getProgressFromSharedPreferences() {
        caloriesProgress.value = sharedPreferences.getFloat("CALORIES_PROGRESS", 0.0f).toDouble()
        proteinProgress.value = sharedPreferences.getFloat("PROTEIN_PROGRESS", 0.0f).toDouble()
        carbohydratesProgress.value = sharedPreferences.getFloat("CARBOHYDRATES_PROGRESS", 0.0f).toDouble()
        fatProgress.value = sharedPreferences.getFloat("FAT_PROGRESS", 0.0f).toDouble()
    }



}