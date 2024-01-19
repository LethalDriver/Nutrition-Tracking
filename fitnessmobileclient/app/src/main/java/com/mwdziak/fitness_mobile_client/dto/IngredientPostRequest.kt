package com.mwdziak.fitness_mobile_client.dto

import kotlinx.serialization.Serializable

@Serializable
data class IngredientPostRequest(
    val fdcId: Int = 0,
    val foodKind: String = "",
    val description: String = "",
    val nutrients: IngredientNutrientsPostRequest = IngredientNutrientsPostRequest(),
    val weight : Double = 0.0,
)
