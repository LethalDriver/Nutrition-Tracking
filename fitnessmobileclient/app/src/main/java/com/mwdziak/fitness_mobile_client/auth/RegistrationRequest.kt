package com.mwdziak.fitness_mobile_client.auth

data class RegistrationRequest(val email: String, val password: String, val firstName: String, val lastName: String)