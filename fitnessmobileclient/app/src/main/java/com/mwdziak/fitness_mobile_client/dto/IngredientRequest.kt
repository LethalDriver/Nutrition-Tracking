package com.mwdziak.fitness_mobile_client.dto

import kotlinx.serialization.Serializable

@Serializable
data class IngredientRequest(
    val fdcId: Int = 0,
    val foodKind: String = "",
    val description: String = "",
    val nutrients: IngredientNutrientsRequest = IngredientNutrientsRequest(),
    val weight : Double = 0.0,
)
