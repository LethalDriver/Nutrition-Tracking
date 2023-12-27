package com.mwdziak.fitness_mobile_client.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mwdziak.fitness_mobile_client.domain.NutritionalGoals
import com.mwdziak.fitness_mobile_client.service.HttpService
import com.mwdziak.fitness_mobile_client.service.Validator
import io.ktor.client.HttpClient

class UpdateGoalsViewModel(private val httpService: HttpService) : ViewModel() {
    private val calories = MutableLiveData<String>("")
    private val protein = MutableLiveData<String>("")
    private val carbohydrates = MutableLiveData<String>("")
    private val fat = MutableLiveData<String>("")

    fun updateCalories(newCalories: String) {
        calories.value = newCalories
    }
    fun updateProtein(newProtein: String) {
        protein.value = newProtein
    }
    fun updateCarbohydrates(newCarbohydrates: String) {
        carbohydrates.value = newCarbohydrates
    }
    fun updateFat(newFat: String) {
        fat.value = newFat
    }

    suspend fun updateGoals() {
        val nutritionalGoals = NutritionalGoals(
            calories = calories.value?.toDoubleOrNull(),
            protein = protein.value?.toDoubleOrNull(),
            carbohydrates = carbohydrates.value?.toDoubleOrNull(),
            fat = fat.value?.toDoubleOrNull()
        )
        httpService.updateGoals(nutritionalGoals)
    }


}