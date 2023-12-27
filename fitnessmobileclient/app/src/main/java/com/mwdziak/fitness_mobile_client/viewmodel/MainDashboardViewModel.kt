package com.mwdziak.fitness_mobile_client.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mwdziak.fitness_mobile_client.service.HttpService

class MainDashboardViewModel(private val httpService: HttpService) : ViewModel() {
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

    private suspend fun isDayCreated(): Boolean {
        return httpService.isDayCreated()
    }

    private suspend fun createDay() {
        httpService.createDay()
    }

    suspend fun onStartup() {
        if (isDayCreated()) {
            getProgress()
        } else {
            createDay()
            caloriesProgress.value = 0.0
            proteinProgress.value = 0.0
            carbohydratesProgress.value = 0.0
            fatProgress.value = 0.0
        }
    }


}