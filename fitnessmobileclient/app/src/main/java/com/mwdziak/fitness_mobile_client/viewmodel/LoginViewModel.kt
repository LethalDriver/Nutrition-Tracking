package com.mwdziak.fitness_mobile_client.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mwdziak.fitness_mobile_client.auth.AuthenticationRequest
import com.mwdziak.fitness_mobile_client.auth.TokensDTO
import com.mwdziak.fitness_mobile_client.auth.TokenManager
import io.ktor.client.HttpClient
import io.ktor.client.call.receive
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

        val url = "http://10.0.2.2:8080/auth/login"

        val response: HttpResponse = client.post(url) {
            contentType(ContentType.Application.Json)
            body = authenticationRequest
        }

        val tokensDTO: TokensDTO = response.receive()
        client.close()

        tokenManager.saveTokens(tokensDTO.token, tokensDTO.refreshToken)
    }
}