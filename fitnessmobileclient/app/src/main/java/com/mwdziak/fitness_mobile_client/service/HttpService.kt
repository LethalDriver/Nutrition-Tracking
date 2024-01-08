package com.mwdziak.fitness_mobile_client.service

import com.mwdziak.fitness_mobile_client.auth.AuthenticationRequest
import com.mwdziak.fitness_mobile_client.auth.RegistrationRequest
import com.mwdziak.fitness_mobile_client.auth.TokensDTO
import com.mwdziak.fitness_mobile_client.domain.NutritionalGoals
import com.mwdziak.fitness_mobile_client.domain.NutritionalProgress
import com.mwdziak.fitness_mobile_client.dto.FoodDTO
import io.ktor.client.HttpClient
import io.ktor.client.call.receive
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType


class HttpService(private val noAuthHttpClient: HttpClient, private val defaultHttpClient: HttpClient) {
    private val mainUrl = "http://10.0.2.2:8080"
    private val foodServiceUrl = "http://10.0.2.2:8000"
    suspend fun authenticate(authenticationRequest: AuthenticationRequest): TokensDTO {
        val url = "$mainUrl/auth/login"

        val response: HttpResponse = noAuthHttpClient.post(url) {
            contentType(ContentType.Application.Json)
            body = authenticationRequest
        }
        val tokensDTO: TokensDTO = response.receive()

        noAuthHttpClient.close()
        return tokensDTO
    }

    suspend fun register(registrationRequest: RegistrationRequest): TokensDTO{
        val url = "$mainUrl/auth/register"

        val response: HttpResponse = noAuthHttpClient.post(url) {
            contentType(ContentType.Application.Json)
            body = registrationRequest
        }

        val tokensDTO: TokensDTO = response.receive()
        noAuthHttpClient.close()

        return tokensDTO
    }

    suspend fun getGoals(): NutritionalGoals {
        val url = "$mainUrl/user/goals/get"
        val response: HttpResponse = defaultHttpClient.get(url)
        val nutritionalGoals: NutritionalGoals = response.receive()
        defaultHttpClient.close()
        return nutritionalGoals
    }

    suspend fun updateGoals(nutritionalGoals: NutritionalGoals) {
        val url = "$mainUrl/user/goals/update"
        val response: HttpResponse = defaultHttpClient.put(url) {
            contentType(ContentType.Application.Json)
            body = nutritionalGoals
        }
        defaultHttpClient.close()
    }

    suspend fun isDayCreated(): Boolean {
        val url = "$mainUrl/user/day/exists"
        val response: HttpResponse = defaultHttpClient.get(url)
        val isDayCreated: Boolean = response.receive()
        defaultHttpClient.close()
        return isDayCreated
    }

    suspend fun createDay() {
        val url = "$mainUrl/user/day/create"
        val response: HttpResponse = defaultHttpClient.post(url)
        defaultHttpClient.close()
    }
    suspend fun getProgress(): NutritionalProgress {
        val url = "$mainUrl/user/day/get"
        val response: HttpResponse = defaultHttpClient.get(url)
        val nutritionalProgress: NutritionalProgress = response.receive()
        defaultHttpClient.close()
        return nutritionalProgress
    }

    suspend fun updateProgress() {
        val url = "$mainUrl/user/day/update"
        val response: HttpResponse = defaultHttpClient.put(url)
        defaultHttpClient.close()
    }

    suspend fun getFoods(foodKind: String): List<FoodDTO> {
        val url = "$foodServiceUrl/food/kinds/$foodKind"
        val response: HttpResponse = defaultHttpClient.get(url)
        val foodDTOs: List<FoodDTO> = response.receive()
        defaultHttpClient.close()
        return foodDTOs
    }




}