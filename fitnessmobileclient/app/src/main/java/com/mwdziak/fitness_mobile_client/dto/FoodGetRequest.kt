package com.mwdziak.fitness_mobile_client.dto

import kotlinx.serialization.Serializable

@Serializable
data class FoodGetRequest(
    val fdcId: Int = 0,
    val foodKind: String = "",
    val description: String = "",
    val nutrients: IngredientNutrientsPostRequest = IngredientNutrientsPostRequest(),
)