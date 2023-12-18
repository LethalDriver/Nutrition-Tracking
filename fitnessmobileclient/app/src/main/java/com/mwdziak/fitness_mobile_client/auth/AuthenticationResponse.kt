package com.mwdziak.fitness_mobile_client.auth

import kotlinx.serialization.Serializable

@Serializable
data class AuthenticationResponse(val token: String, val email: String, val refreshToken: String)