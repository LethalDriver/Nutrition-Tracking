package com.mwdziak.fitness_mobile_client.auth

import kotlinx.serialization.Serializable

@Serializable
data class TokensDTO(val token: String, val refreshToken: String)