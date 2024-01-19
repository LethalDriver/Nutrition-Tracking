package com.mwdziak.fitness_mobile_client.dto

import kotlinx.serialization.Serializable

@Serializable

data class MealPostRequest (
    val name: String = "",
    val ingredients: List<IngredientPostRequest> = listOf(),
)
