package com.mwdziak.fitness_mobile_client.dto

import kotlinx.serialization.Serializable
import java.time.LocalDate

@Serializable
data class DayRequest(val id: Long,
                      val date: String,
                      val meals: List<MealRequest>)
