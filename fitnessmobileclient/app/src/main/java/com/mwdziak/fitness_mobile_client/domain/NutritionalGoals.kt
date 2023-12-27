package com.mwdziak.fitness_mobile_client.domain
import kotlinx.serialization.Serializable

@Serializable
data class NutritionalGoals(
    val calories: Double? = null,
    val protein: Double? = null,
    val carbohydrates: Double? = null,
    val fat: Double? = null,
)