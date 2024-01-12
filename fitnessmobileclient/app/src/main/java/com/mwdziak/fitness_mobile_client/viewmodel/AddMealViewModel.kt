package com.mwdziak.fitness_mobile_client.viewmodel

import androidx.lifecycle.ViewModel
import com.mwdziak.fitness_mobile_client.dto.FoodGetRequest
import com.mwdziak.fitness_mobile_client.dto.FoodPostRequest
import com.mwdziak.fitness_mobile_client.service.HttpService

class AddMealViewModel(private val httpService: HttpService) : ViewModel() {
    private val forms = mutableListOf<IngredientFormViewModel>()
    private val foodKinds = mutableListOf("Fruit", "Egg", "Meat", "Dairy", "Grain", "Other")
    private val areAllFieldsValid = false

    fun addIngredient(formViewModel: IngredientFormViewModel) {
        forms.add(formViewModel)
    }
    fun removeIngredient(formViewModel: IngredientFormViewModel) {
        forms.remove(formViewModel)
    }

    suspend fun fetchFoodKinds() {
        val foods = httpService.getFoodKinds()
        val kinds = mapFoodDtoToKinds(foods)
        foodKinds.clear()
        foodKinds.addAll(kinds)
    }

    private fun mapFoodDtoToKinds(foods: List<FoodGetRequest>): List<String> {
        return foods.map { it.foodKind }
    }

    fun getFoodCategories(): List<String> {
        return foodKinds
    }
    fun checkIfAllFieldsValid(): Boolean {
        return forms.all { it.getIsAllFieldsValid() }
    }

    fun MapFormsToFoodPostRequest(): List<FoodPostRequest> {
        return forms.map { it.mapFormToFoodPostRequest() }
    }
}