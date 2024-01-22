package com.mwdziak.fitness_mobile_client.auth

data class RefreshRequest(
    val token: String,
    val refreshToken: String
)
