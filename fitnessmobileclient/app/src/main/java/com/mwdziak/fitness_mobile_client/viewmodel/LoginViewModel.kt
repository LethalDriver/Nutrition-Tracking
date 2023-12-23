package com.mwdziak.fitness_mobile_client.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mwdziak.fitness_mobile_client.auth.AuthenticationRequest
import com.mwdziak.fitness_mobile_client.auth.TokensDTO
import com.mwdziak.fitness_mobile_client.service.HttpService
import com.mwdziak.fitness_mobile_client.service.TokenManager
import io.ktor.client.call.receive
import io.ktor.client.request.post
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType

class LoginViewModel(private val tokenManager: TokenManager, private val httpService: HttpService) : ViewModel() {
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

        val tokensDTO = httpService.authenticate(authenticationRequest)

        tokenManager.saveTokens(tokensDTO.token, tokensDTO.refreshToken)
    }

}