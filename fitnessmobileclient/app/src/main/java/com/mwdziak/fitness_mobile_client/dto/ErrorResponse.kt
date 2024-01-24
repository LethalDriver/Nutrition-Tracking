package com.mwdziak.fitness_mobile_client.dto

data class ErrorResponse(val type: String,
                         val title: String,
                         val status: Int,
                         val detail: String,
                         val instance: String)
