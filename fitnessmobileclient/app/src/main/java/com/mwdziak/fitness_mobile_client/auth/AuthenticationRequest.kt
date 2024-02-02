package com.mwdziak.fitness_mobile_client.auth

import kotlinx.serialization.Serializable

@Serializable
data class AuthenticationRequest(
    val email: String,
    val password: String
)