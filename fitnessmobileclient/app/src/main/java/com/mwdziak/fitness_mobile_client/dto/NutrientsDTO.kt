package com.mwdziak.fitness_mobile_client.dto

import kotlinx.serialization.Serializable

@Serializable
data class NutrientsDTO(
    val calories: Double,
    val protein: Double,
    val carbohydrates: Double,
    val fat: Double)