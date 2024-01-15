package com.mwdziak.fitness_mobile_client.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mwdziak.fitness_mobile_client.dto.FoodGetRequest
import com.mwdziak.fitness_mobile_client.dto.IngredientPostRequest
import com.mwdziak.fitness_mobile_client.dto.IngredientNutrientsPostRequest
import com.mwdziak.fitness_mobile_client.service.HttpService
import com.mwdziak.fitness_mobile_client.service.Validator

class IngredientFormViewModel(private val httpService: HttpService, private val validator: Validator) : ViewModel(){
    private val foodsMatchingKind = mutableListOf<FoodGetRequest>()
    private val foodDescriptions = mutableListOf<String>()
    private val foodKinds = mutableListOf<String>()
    private var foodFromDatabase = FoodGetRequest()
    private val pickedFoodKind = MutableLiveData<String>("")
    private val pickedFoodDescription = MutableLiveData<String>("")
    private val pickedFoodWeight = MutableLiveData<Double>(0.0)
    private val pickedWeightUnit = MutableLiveData<String>("")
    private val weightUnits = arrayOf("g", "kg", "lb")
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

    fun updatePickedFoodDescription(dropdownPosition: Int) {
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

    private fun calculateNutritionalValues(): IngredientNutrientsPostRequest {
        val weight = pickedFoodWeight.value ?: 0.0
        val multiplier = when(pickedWeightUnit.value) {
            "g" -> 1.0
            "kg" -> 1000.0
            "lb" -> 453.592
            else -> 1.0
        }
        val multiplierWeight = weight * multiplier
        return IngredientNutrientsPostRequest(
            calories = foodFromDatabase.nutrients.calories * multiplierWeight,
            protein = foodFromDatabase.nutrients.protein * multiplierWeight,
            carbohydrates = foodFromDatabase.nutrients.carbohydrates * multiplierWeight,
            fat = foodFromDatabase.nutrients.fat * multiplierWeight,
        )
    }

    private fun checkFormValidity() {
        isAllFieldsValid = isFoodKindValid() &&
                isFoodDescriptionValid() &&
                isFoodWeightValid() &&
                isWeightUnitValid()
    }

    private fun isFoodKindValid(): Boolean {
        return validator.isInCollection(pickedFoodKind.value ?: "", foodKinds)
    }

    private fun isFoodDescriptionValid(): Boolean {
        return validator.isInCollection(pickedFoodDescription.value ?: "", foodDescriptions)
    }

    private fun isFoodWeightValid(): Boolean {
        return validator.isNotZero(pickedFoodWeight.value ?: 0.0)
    }

    private fun isWeightUnitValid(): Boolean {
        return validator.isInCollection(pickedWeightUnit.value ?: "", weightUnits.toList())
    }

    fun getIsAllFieldsValid(): Boolean {
        return isAllFieldsValid
    }

    fun mapFormToFoodPostRequest(): IngredientPostRequest {
        return IngredientPostRequest(
            fdcId = foodFromDatabase.fdcId,
            foodKind = pickedFoodKind.value ?: "",
            description = pickedFoodDescription.value ?: "",
            nutrients = calculateNutritionalValues(),
            weight = pickedFoodWeight.value ?: 0.0,
        )
    }

    fun updatePickedFoodDescription(s: String) {
        pickedFoodDescription.value = s
        checkFormValidity()
    }

    fun updateFoodKinds(foodKinds: MutableList<String>) {
        this.foodKinds.clear()
        this.foodKinds.addAll(foodKinds)
    }

    override fun toString(): String {
        return "IngredientFormViewModel(foodsMatchingKind=$foodsMatchingKind, foodDescriptions=$foodDescriptions, foodKinds=$foodKinds, foodFromDatabase=$foodFromDatabase, pickedFoodKind=$pickedFoodKind, pickedFoodDescription=$pickedFoodDescription, pickedFoodWeight=$pickedFoodWeight, pickedWeightUnit=$pickedWeightUnit, weightUnits=${weightUnits.contentToString()}, isAllFieldsValid=$isAllFieldsValid)"
    }
}