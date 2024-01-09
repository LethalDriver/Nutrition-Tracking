package com.mwdziak.fitness_mobile_client.dto

import kotlinx.serialization.Serializable

@Serializable
data class FoodDTO(
    val fdcId: Int,
    val foodKind: String,
    val description: String,
    val calories: Double,
    val protein: Double,
    val carbohydrates: Double,
    val fat: Double
)