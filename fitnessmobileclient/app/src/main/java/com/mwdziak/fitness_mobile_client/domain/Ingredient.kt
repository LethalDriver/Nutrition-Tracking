package com.mwdziak.fitness_mobile_client.domain

data class Ingredient(
    val id: Int = 0,
    var foodKind: String = "",
    var description: String = "",
    var weight: Double = 0.0,
    var unit: String = "",
    val calories: Double = 0.0,
    val protein: Double = 0.0,
    val carbohydrates: Double = 0.0,
    val fat: Double = 0.0
)