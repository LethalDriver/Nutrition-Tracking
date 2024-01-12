package com.mwdziak.fitness_mobile_client.service

import com.mwdziak.fitness_mobile_client.auth.AuthenticationRequest
import com.mwdziak.fitness_mobile_client.auth.RegistrationRequest
import com.mwdziak.fitness_mobile_client.auth.TokensDTO
import com.mwdziak.fitness_mobile_client.dto.NutritionalGoalsRequest
import com.mwdziak.fitness_mobile_client.dto.NutritionalProgressRequest
import com.mwdziak.fitness_mobile_client.dto.FoodGetRequest
import io.ktor.client.HttpClient
import io.ktor.client.call.receive
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType


class HttpService(private val noAuthHttpClient: HttpClient, private val defaultHttpClient: HttpClient) {
    private val mainUrl = "http://10.0.2.2:8080"
    private val foodServiceUrl = "http://10.0.2.2:8081"
    suspend fun authenticate(authenticationRequest: AuthenticationRequest): TokensDTO {
        val url = "$mainUrl/auth/login"

        val response: HttpResponse = noAuthHttpClient.post(url) {
            contentType(ContentType.Application.Json)
            body = authenticationRequest
        }

        return response.receive()
    }

    suspend fun register(registrationRequest: RegistrationRequest): TokensDTO {
        val url = "$mainUrl/auth/register"

        val response: HttpResponse = noAuthHttpClient.post(url) {
            contentType(ContentType.Application.Json)
            body = registrationRequest
        }

        return response.receive()
    }

    suspend fun getGoals(): NutritionalGoalsRequest {
        val url = "$mainUrl/user/goals/get"
        val response: HttpResponse = defaultHttpClient.get(url)
        return response.receive()
    }

    suspend fun updateGoals(nutritionalGoals: NutritionalGoalsRequest) {
        val url = "$mainUrl/user/goals/update"
        val response: HttpResponse = defaultHttpClient.put(url) {
            contentType(ContentType.Application.Json)
            body = nutritionalGoals
        }
    }

    suspend fun isDayCreated(): Boolean {
        val url = "$mainUrl/user/day/exists"
        val response: HttpResponse = defaultHttpClient.get(url)
        return response.receive()
    }

    suspend fun createDay() {
        val url = "$mainUrl/user/day/create"
        val response: HttpResponse = defaultHttpClient.post(url)
    }
    suspend fun getProgress(): NutritionalProgressRequest {
        val url = "$mainUrl/user/day/get"
        val response: HttpResponse = defaultHttpClient.get(url)
        return response.receive()
    }

    suspend fun updateProgress() {
        val url = "$mainUrl/user/day/update"
        val response: HttpResponse = defaultHttpClient.put(url)
    }

    suspend fun getFoodsByKind(foodKind: String): List<FoodGetRequest> {
        val url = "$foodServiceUrl/food/kinds"
        val response: HttpResponse = noAuthHttpClient.get(url) {
            parameter("foodKind", foodKind)
        }
        return response.receive()
    }

    suspend fun getFoodKinds(): List<FoodGetRequest> {
        val url = "$foodServiceUrl/food/kinds"
        val response: HttpResponse = noAuthHttpClient.get(url)
        return response.receive()
    }
}