package com.mwdziak.fitness_mobile_client.dto

import kotlinx.serialization.Serializable

@Serializable
data class IngredientNutrientsRequest(
    val calories: Double = 0.0,
    val protein: Double = 0.0,
    val carbohydrates: Double = 0.0,
    val fat: Double = 0.0,)