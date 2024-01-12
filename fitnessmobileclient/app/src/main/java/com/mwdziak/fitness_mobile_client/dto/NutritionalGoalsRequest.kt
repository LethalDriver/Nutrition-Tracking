package com.mwdziak.fitness_mobile_client.dto
import kotlinx.serialization.Serializable

@Serializable
data class NutritionalGoalsRequest(
    val calories: Double? = null,
    val protein: Double? = null,
    val carbohydrates: Double? = null,
    val fat: Double? = null,
)