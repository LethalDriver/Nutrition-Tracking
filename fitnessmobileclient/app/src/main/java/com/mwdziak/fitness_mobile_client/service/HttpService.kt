package com.mwdziak.fitness_mobile_client.service

import com.mwdziak.fitness_mobile_client.auth.AuthenticationRequest
import com.mwdziak.fitness_mobile_client.auth.RegistrationRequest
import com.mwdziak.fitness_mobile_client.auth.TokensDTO
import com.mwdziak.fitness_mobile_client.domain.NutritionalGoals
import io.ktor.client.HttpClient
import io.ktor.client.call.receive
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType


class HttpService(private val noAuthHttpClient: HttpClient, private val defaultHttpClient: HttpClient) {
    suspend fun authenticate(authenticationRequest: AuthenticationRequest): TokensDTO {
        val url = "http://10.0.2.2:8080/auth/login"

        val response: HttpResponse = noAuthHttpClient.post(url) {
            contentType(ContentType.Application.Json)
            body = authenticationRequest
        }
        val tokensDTO: TokensDTO = response.receive()

        noAuthHttpClient.close()
        return tokensDTO
    }

    suspend fun register(registrationRequest: RegistrationRequest): TokensDTO{
        val url = "http://10.0.2.2:8080/auth/register"

        val response: HttpResponse = noAuthHttpClient.post(url) {
            contentType(ContentType.Application.Json)
            body = registrationRequest
        }

        val tokensDTO: TokensDTO = response.receive()
        noAuthHttpClient.close()

        return tokensDTO
    }

    suspend fun updateGoals(nutritionalGoals: NutritionalGoals) {
        val url = "http://10.0.2.2:8080/user/goals/update"
        val response: HttpResponse = defaultHttpClient.put(url) {
            contentType(ContentType.Application.Json)
            body = nutritionalGoals
        }
        defaultHttpClient.close()
    }


}