package com.mwdziak.fitness_mobile_client.viewmodel

import androidx.lifecycle.ViewModel
import com.mwdziak.fitness_mobile_client.dto.FoodDTO
import com.mwdziak.fitness_mobile_client.service.HttpService

class AddMealViewModel(private val httpService: HttpService) : ViewModel() {
    private val forms = mutableListOf<IngredientFormViewModel>()
    private val foodKinds = mutableListOf("Fruit", "Egg", "Meat", "Dairy", "Grain", "Other")

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

    private fun mapFoodDtoToKinds(foods: List<FoodDTO>): List<String> {
        return foods.map { it.foodKind }
    }

    fun getFoodCategories(): List<String> {
        return foodKinds
    }
}