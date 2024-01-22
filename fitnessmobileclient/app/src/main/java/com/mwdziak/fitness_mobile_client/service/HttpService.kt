package com.mwdziak.fitness_mobile_client.service

import com.mwdziak.fitness_mobile_client.auth.AuthenticationRequest
import com.mwdziak.fitness_mobile_client.auth.RegistrationRequest
import com.mwdziak.fitness_mobile_client.auth.AuthenticationResponse
import com.mwdziak.fitness_mobile_client.dto.DayRequest
import com.mwdziak.fitness_mobile_client.dto.NutritionalGoalsGetRequest
import com.mwdziak.fitness_mobile_client.dto.NutritionalProgressGetRequest
import com.mwdziak.fitness_mobile_client.dto.FoodGetRequest
import com.mwdziak.fitness_mobile_client.dto.MealRequest
import io.ktor.client.HttpClient
import io.ktor.client.call.receive
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentLength
import io.ktor.http.contentType


class HttpService(private val noAuthHttpClient: HttpClient, private val defaultHttpClient: HttpClient) {
    private val mainUrl = "http://10.0.2.2:8080"
    private val foodServiceUrl = "http://10.0.2.2:8081"
    suspend fun authenticate(authenticationRequest: AuthenticationRequest): AuthenticationResponse {
        val url = "$mainUrl/auth/login"

        val response: HttpResponse = noAuthHttpClient.post(url) {
            contentType(ContentType.Application.Json)
            body = authenticationRequest
        }

        return response.receive()
    }

    suspend fun register(registrationRequest: RegistrationRequest): AuthenticationResponse {
        val url = "$mainUrl/auth/register"

        val response: HttpResponse = noAuthHttpClient.post(url) {
            contentType(ContentType.Application.Json)
            body = registrationRequest
        }

        return response.receive()
    }

    suspend fun getGoals(): NutritionalGoalsGetRequest {
        val url = "$mainUrl/user/goals"
        val response: HttpResponse = defaultHttpClient.get(url)
        return response.receive()
    }

    suspend fun updateGoals(nutritionalGoals: NutritionalGoalsGetRequest) {
        val url = "$mainUrl/user/goals"
        val response: HttpResponse = defaultHttpClient.put(url) {
            contentType(ContentType.Application.Json)
            body = nutritionalGoals
        }
    }
    suspend fun getProgress(): NutritionalProgressGetRequest {
        val url = "$mainUrl/user/day/progress"
        val response: HttpResponse = defaultHttpClient.get(url)
        return response.receive()
    }

    suspend fun getFoodsByKind(foodKind: String): List<FoodGetRequest> {
        val url = "$foodServiceUrl/food/kinds"
        val response: HttpResponse = noAuthHttpClient.get(url) {
            parameter("foodKind", foodKind)
        }
        return response.receive()
    }

    suspend fun getFoodKinds(): List<String> {
        val url = "$foodServiceUrl/food/kinds/all"
        val response: HttpResponse = noAuthHttpClient.get(url)
        return response.receive()
    }

    suspend fun postMeal(mealRequest: MealRequest) {
        val url = "$mainUrl/user/day/meals"
        val response: HttpResponse = defaultHttpClient.post(url) {
            contentType(ContentType.Application.Json)
            body = mealRequest
        }
    }

    suspend fun getUserDays(): List<DayRequest> {
        val url = "$mainUrl/user/days"
        val response: HttpResponse = defaultHttpClient.get(url)
        if (response.status == HttpStatusCode.OK) {
            if (response.contentLength() != 0L) {
                return response.receive()
            }
        }
        return listOf()
    }
}