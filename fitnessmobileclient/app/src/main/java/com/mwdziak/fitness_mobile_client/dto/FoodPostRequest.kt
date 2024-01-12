package com.mwdziak.fitness_mobile_client.dto

data class FoodPostRequest(
    val fdcId: Int = 0,
    val foodKind: String = "",
    val description: String = "",
    val nutrients: NutrientsRequest = NutrientsRequest(),
    val weight : Double = 0.0,
)
