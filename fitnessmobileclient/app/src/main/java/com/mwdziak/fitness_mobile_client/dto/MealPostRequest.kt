package com.mwdziak.fitness_mobile_client.dto

data class MealPostRequest (
    val mealName: String = "",
    val foods: List<IngredientPostRequest> = listOf(),
)
