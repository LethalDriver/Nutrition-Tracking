package com.mwdziak.fitness_mobile_client.viewmodel

import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mwdziak.fitness_mobile_client.auth.AuthenticationResponse
import com.mwdziak.fitness_mobile_client.auth.RegistrationRequest
import com.mwdziak.fitness_mobile_client.auth.TokenManager
import io.ktor.client.HttpClient
import io.ktor.client.call.receive
import io.ktor.client.request.post
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType

class RegisterViewModel(private val client: HttpClient, private val tokenManager: TokenManager): ViewModel() {
    private val email = MutableLiveData<String>("")
    private val password = MutableLiveData<String>("")
    private val firstName = MutableLiveData<String>("")
    private val lastName = MutableLiveData<String>("")

    fun updateEmail(newEmail: String) {
        email.value = newEmail
    }

    fun updatePassword(newPassword: String) {
        password.value = newPassword
    }

    fun updateFirstName(newFirstName: String) {
        firstName.value = newFirstName
    }

    fun updateLastName(newLastName: String) {
        lastName.value = newLastName
    }


    suspend fun register(){

        val registrationRequest = RegistrationRequest(email.value ?: "", password.value ?: "",
            firstName.value ?: "", lastName.value ?: "")

        val url = "http://10.0.2.2:8080/auth/register"

        val response: HttpResponse = client.post(url) {
            contentType(ContentType.Application.Json)
            body = registrationRequest
        }

        val authenticationResponse: AuthenticationResponse = response.receive()
        client.close()

        tokenManager.saveTokens(authenticationResponse.token, authenticationResponse.refreshToken)
    }
}