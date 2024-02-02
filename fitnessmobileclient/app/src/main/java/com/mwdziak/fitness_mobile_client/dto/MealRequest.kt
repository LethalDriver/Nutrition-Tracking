package com.mwdziak.fitness_mobile_client.dto

import kotlinx.serialization.Serializable

@Serializable

data class MealRequest (
    val name: String = "",
    val ingredients: List<IngredientRequest> = listOf(),
)
