package com.mwdziak.fitness_mobile_client.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mwdziak.fitness_mobile_client.dto.FoodDTO
import com.mwdziak.fitness_mobile_client.service.HttpService

class IngredientFormViewModel(private val httpService: HttpService) : ViewModel(){
    private val foodDescriptions = mutableListOf<String>()
    private val pickedFoodKind = MutableLiveData<String>("")
    private val pickedFoodDescription = MutableLiveData<String>("")
    private val pickedFoodWeight = MutableLiveData<Double>(0.0)
    private val pickedWeightUnit = MutableLiveData<String>("")
    private val weightUnits = arrayOf("g", "kg", "lb")
    private val calories: Double = 0.0
    private val protein: Double = 0.0
    private val carbohydrates: Double = 0.0
    private val fat: Double = 0.0
    private val caloriesFactor: Double = 0.0
    private val proteinFactor: Double = 0.0
    private val carbohydratesFactor: Double = 0.0
    private val fatFactor: Double = 0.0


    private fun mapFoodDtoToDescriptions(foods: List<FoodDTO>): List<String> {
        return foods.map { it.description }
    }

    suspend fun fetchFoodDescriptions() {
        val foods = httpService.getFoodsByKind(pickedFoodKind.value ?: "")
        val descriptions = mapFoodDtoToDescriptions(foods)
        foodDescriptions.clear()
        foodDescriptions.addAll(descriptions)
    }


    fun getFoodDescriptions(): List<String> {
        return foodDescriptions
    }

    fun getWeightUnits(): Array<String> {
        return weightUnits
    }

    fun updatePickedFoodKind(foodCategory: String) {
        pickedFoodKind.value = foodCategory
    }

    fun updatePickedFoodDescription(dropdownPosition: Int) {
        pickedFoodDescription.value = foodDescriptions[dropdownPosition]
    }

    fun updatePickedFoodWeight(newFoodWeight: Double) {
        pickedFoodWeight.value = newFoodWeight
    }

    fun updatePickedWeightUnit(dropdownPosition: Int) {
        pickedWeightUnit.value = weightUnits[dropdownPosition]
    }

}