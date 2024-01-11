package com.mwdziak.fitness_mobile_client.viewmodel

import androidx.lifecycle.ViewModel
import com.mwdziak.fitness_mobile_client.R
import com.mwdziak.fitness_mobile_client.dto.FoodDTO
import com.mwdziak.fitness_mobile_client.service.HttpService

class IngredientFormViewModel(private val httpService: HttpService) : ViewModel(){
    val foodCategories = listOf("Fruit", "Egg", "Meat", "Dairy", "Grain", "Other")

    suspend fun getFoods(foodKind: String): List<FoodDTO> {
        val foods = httpService.getFoods(foodKind)
        return foods
    }

    fun getFoodsDescriptions(foods: List<FoodDTO>): List<String> {
        return foods.map { it.description }
    }

}