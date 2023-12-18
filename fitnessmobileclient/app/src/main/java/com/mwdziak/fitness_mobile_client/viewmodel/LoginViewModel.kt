package com.mwdziak.fitness_mobile_client.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mwdziak.fitness_mobile_client.auth.AuthenticationRequest
import com.mwdziak.fitness_mobile_client.auth.AuthenticationResponse
import com.mwdziak.fitness_mobile_client.auth.TokenManager
import io.ktor.client.HttpClient
import io.ktor.client.call.receive
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.post
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType

class LoginViewModel(private val client: HttpClient, private val tokenManager: TokenManager) : ViewModel() {
    private val email = MutableLiveData<String>("")
    private val password = MutableLiveData<String>("")

    fun updateEmail(newEmail: String) {
        email.value = newEmail
    }

    fun updatePassword(newPassword: String) {
        password.value = newPassword
    }

    suspend fun authenticate(){

        val authenticationRequest = AuthenticationRequest(email.value ?: "", password.value ?: "")

        val url = "https://example.com/api/endpoint"

        val response: HttpResponse = client.post(url) {
            contentType(ContentType.Application.Json)
            body = authenticationRequest
        }

        val authenticationResponse: AuthenticationResponse = response.receive()
        client.close()

        tokenManager.saveTokens(authenticationResponse.token, authenticationResponse.refreshToken)
    }
}