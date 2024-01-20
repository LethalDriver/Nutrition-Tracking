package com.mwdziak.fitness_mobile_client.viewmodel

import android.content.SharedPreferences
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
    private val previousProgressState = mutableMapOf(
        "CALORIES_PROGRESS" to 0.0f,
        "PROTEIN_PROGRESS" to 0.0f,
        "CARBOHYDRATES_PROGRESS" to 0.0f,
        "FAT_PROGRESS" to 0.0f
    )

    suspend fun getGoals() {
        val goals = httpService.getGoals()
        caloriesGoal.value = goals.calories ?: 0.0
        proteinGoal.value = goals.protein ?: 0.0
        carbohydratesGoal.value = goals.carbohydrates ?: 0.0
        fatGoal.value = goals.fat ?: 0.0
    }

    suspend fun getProgress() {
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
        val caloriesProgress = sharedPreferences.getFloat("CALORIES_PROGRESS", 0.0f)
        val proteinProgress = sharedPreferences.getFloat("PROTEIN_PROGRESS", 0.0f)
        val carbohydratesProgress = sharedPreferences.getFloat("CARBOHYDRATES_PROGRESS", 0.0f)
        val fatProgress = sharedPreferences.getFloat("FAT_PROGRESS", 0.0f)
        previousProgressState["CALORIES_PROGRESS"] = caloriesProgress
        previousProgressState["PROTEIN_PROGRESS"] = proteinProgress
        previousProgressState["CARBOHYDRATES_PROGRESS"] = carbohydratesProgress
        previousProgressState["FAT_PROGRESS"] = fatProgress
    }

    fun getPreviousProgressState(s: String): Float {
        return previousProgressState[s] ?: 0.0f
    }


}