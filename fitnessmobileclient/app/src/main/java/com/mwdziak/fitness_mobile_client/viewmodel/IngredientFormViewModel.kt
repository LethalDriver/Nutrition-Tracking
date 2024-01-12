package com.mwdziak.fitness_mobile_client.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mwdziak.fitness_mobile_client.dto.FoodGetRequest
import com.mwdziak.fitness_mobile_client.dto.FoodPostRequest
import com.mwdziak.fitness_mobile_client.dto.NutrientsRequest
import com.mwdziak.fitness_mobile_client.service.HttpService

class IngredientFormViewModel(private val httpService: HttpService) : ViewModel(){
    private val foodsMatchingKind = mutableListOf<FoodGetRequest>()
    private val foodDescriptions = mutableListOf<String>("Raw", "Cooked", "Dry", "Other")
    private val foodKinds = mutableListOf<String>()
    private var foodFromDatabase = FoodGetRequest()
    private val pickedFoodKind = MutableLiveData<String>("")
    private val pickedFoodDescription = MutableLiveData<String>("")
    private val pickedFoodWeight = MutableLiveData<Double>(0.0)
    private val pickedWeightUnit = MutableLiveData<String>("")
    private val weightUnits = arrayOf("g", "kg", "lb")
    private var calories: Double = 0.0
    private var protein: Double = 0.0
    private var carbohydrates: Double = 0.0
    private var fat: Double = 0.0
    private var isAllFieldsValid = false

    private fun mapFoodDtoToDescriptions(foods: List<FoodGetRequest>): List<String> {
        return foods.map { it.description }
    }

    suspend fun fetchFoodsByKind() {
        val foods = httpService.getFoodsByKind(pickedFoodKind.value ?: "")
        foodsMatchingKind.clear()
        foodsMatchingKind.addAll(foods)
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
        checkFormValidity()
    }

    fun updatePickedFood(dropdownPosition: Int) {
        pickedFoodDescription.value = foodDescriptions[dropdownPosition]
        foodFromDatabase = foodsMatchingKind[dropdownPosition]
        checkFormValidity()
    }
    fun updatePickedFoodWeight(newFoodWeight: Double) {
        pickedFoodWeight.value = newFoodWeight
        checkFormValidity()
    }

    fun updatePickedWeightUnit(dropdownPosition: Int) {
        pickedWeightUnit.value = weightUnits[dropdownPosition]
        checkFormValidity()
    }
    fun setFoodKinds(foodKinds: List<String>) {
        this.foodKinds.clear()
        this.foodKinds.addAll(foodKinds)
    }

    fun calculateNutritionalValues(){
        val weight = pickedFoodWeight.value ?: 0.0
        val multiplier = when(pickedWeightUnit.value) {
            "g" -> 1.0
            "kg" -> 1000.0
            "lb" -> 453.592
            else -> 1.0
        }
        val multiplierWeight = weight * multiplier
        calories = foodFromDatabase.nutrients.calories * multiplierWeight
        protein = foodFromDatabase.nutrients.protein * multiplierWeight
        carbohydrates = foodFromDatabase.nutrients.carbohydrates * multiplierWeight
        fat = foodFromDatabase.nutrients.fat * multiplierWeight
    }

    private fun checkFormValidity() {
        isAllFieldsValid = isFoodKindValid() &&
                isFoodDescriptionValid() &&
                isFoodWeightValid() &&
                isWeightUnitValid()
    }

    private fun isFoodKindValid(): Boolean {
        return pickedFoodKind.value in foodKinds
    }

    private fun isFoodDescriptionValid(): Boolean {
        return pickedFoodDescription.value in foodDescriptions
    }

    private fun isFoodWeightValid(): Boolean {
        return pickedFoodWeight.value != 0.0
    }

    private fun isWeightUnitValid(): Boolean {
        return pickedWeightUnit.value in weightUnits
    }

    fun getIsAllFieldsValid(): Boolean {
        return isAllFieldsValid
    }

    fun mapFormToFoodPostRequest(): FoodPostRequest {
        calculateNutritionalValues()
        val nutrients = NutrientsRequest(
            calories = calories,
            protein = protein,
            carbohydrates = carbohydrates,
            fat = fat,
        )
        return FoodPostRequest(
            fdcId = foodFromDatabase.fdcId,
            foodKind = pickedFoodKind.value ?: "",
            description = pickedFoodDescription.value ?: "",
            nutrients = nutrients,
            weight = pickedFoodWeight.value ?: 0.0,
        )
    }

}