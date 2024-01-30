package com.mwdziak.fitness_mobile_client.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mwdziak.fitness_mobile_client.dto.NutritionalGoalsGetRequest
import com.mwdziak.fitness_mobile_client.service.HttpService
import kotlinx.coroutines.launch

class UpdateGoalsViewModel(private val httpService: HttpService) : ViewModel() {
    private val calories = MutableLiveData<String>("")
    private val protein = MutableLiveData<String>("")
    private val carbohydrates = MutableLiveData<String>("")
    private val fat = MutableLiveData<String>("")
    private val updateGoalsComplete = MutableLiveData<Boolean>(false)

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

    fun getUpdateGoalsComplete(): LiveData<Boolean> {
        return updateGoalsComplete
    }

    fun updateGoals() {
        viewModelScope.launch {
            val nutritionalGoals = NutritionalGoalsGetRequest(
                calories = calories.value?.toDoubleOrNull(),
                protein = protein.value?.toDoubleOrNull(),
                carbohydrates = carbohydrates.value?.toDoubleOrNull(),
                fat = fat.value?.toDoubleOrNull()
            )
            httpService.updateGoals(nutritionalGoals)
            updateGoalsComplete.postValue(true)
        }
    }


}