package com.mwdziak.fitness_mobile_client.viewmodel

import androidx.lifecycle.ViewModel
import com.mwdziak.fitness_mobile_client.domain.Ingredient
import com.mwdziak.fitness_mobile_client.dto.FoodDTO
import com.mwdziak.fitness_mobile_client.service.HttpService

class AddMealViewModel(private val httpService: HttpService) : ViewModel() {
    val ingredients = mutableListOf<Ingredient>()
    val forms = mutableListOf<IngredientFormViewModel>()



}