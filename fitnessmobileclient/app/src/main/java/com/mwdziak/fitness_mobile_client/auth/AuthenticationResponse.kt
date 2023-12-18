package com.mwdziak.fitness_mobile_client.auth

data class AuthenticationResponse(val token: String, val email: String, val refreshToken: String)