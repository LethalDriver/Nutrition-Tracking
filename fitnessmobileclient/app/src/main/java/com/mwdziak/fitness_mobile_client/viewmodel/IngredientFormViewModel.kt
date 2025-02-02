package com.mwdziak.fitness_mobile_client.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mwdziak.fitness_mobile_client.dto.FoodGetRequest
import com.mwdziak.fitness_mobile_client.dto.IngredientRequest
import com.mwdziak.fitness_mobile_client.dto.IngredientNutrientsRequest
import com.mwdziak.fitness_mobile_client.service.HttpService
import com.mwdziak.fitness_mobile_client.service.Validator
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class IngredientFormViewModel(private val httpService: HttpService, private val validator: Validator) : ViewModel(){
    private val foodsMatchingKind = mutableListOf<FoodGetRequest>()
    private val foodDescriptions = MutableLiveData<MutableList<String>>(mutableListOf())
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

    fun fetchFoodsByKind() {
        viewModelScope.launch {
            val foods = async { httpService.getFoodsByKind(pickedFoodKind.value ?: "") }.await()
            foodsMatchingKind.clear()
            foodsMatchingKind.addAll(foods)
            val descriptions = mapFoodDtoToDescriptions(foods)
            foodDescriptions.value?.clear()
            foodDescriptions.value?.addAll(descriptions)
        }
    }

    fun getFoodDescriptions(): LiveData<MutableList<String>> {
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
        pickedFoodDescription.value = foodDescriptions.value?.get(dropdownPosition) ?: ""
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

    private fun calculateNutritionalValues(): IngredientNutrientsRequest {
        val weight = pickedFoodWeight.value ?: 0.0
        val multiplier = when(pickedWeightUnit.value) {
            "g" -> 0.001
            "kg" -> 1.0
            "lb" -> 0.453592
            else -> 1.0
        }
        val multiplierWeight = weight * multiplier
        pickedFoodWeight.value = multiplierWeight * 1000
        return IngredientNutrientsRequest(
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
        return validator.isInCollection(pickedFoodDescription.value ?: "",
            foodDescriptions.value ?: listOf())
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

    fun mapFormToFoodPostRequest(): IngredientRequest {
        return IngredientRequest(
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