package com.mwdziak.fitness_mobile_client.service

import android.content.Context
import android.content.SharedPreferences
import com.mwdziak.fitness_mobile_client.MyApplication
import com.mwdziak.fitness_mobile_client.auth.TokensDTO
import io.ktor.client.HttpClient
import io.ktor.client.call.receive
import io.ktor.client.request.post
import io.ktor.client.statement.HttpResponse

class TokenManager(private val httpClient: HttpClient, private val sharedPreferences: SharedPreferences) {
    fun saveTokens(jwtToken: String, refreshToken: String) {
        val editor = sharedPreferences.edit()
        editor.putString("JWT_TOKEN", jwtToken)
        editor.putString("REFRESH_TOKEN", refreshToken)
        editor.apply()
    }

    fun deleteTokens() {
        val editor = sharedPreferences.edit()
        editor.remove("JWT_TOKEN")
        editor.remove("REFRESH_TOKEN")
        editor.apply()
    }

    fun getJwtToken(): String? {
        return sharedPreferences.getString("JWT_TOKEN", null)
    }

    fun getRefreshToken(): String? {
        return sharedPreferences.getString("REFRESH_TOKEN", null)
    }

    suspend fun refreshTokens() {
        val token = getRefreshToken()
        val refreshToken = getRefreshToken()
        val tokens = TokensDTO(token!!, refreshToken!!)
        val url = "http://10.0.2.2:8080/auth/refresh"
        val response: HttpResponse = httpClient.post(url) {
            body = tokens
        }
        val newTokens: TokensDTO = response.receive()
        saveTokens(newTokens.token, newTokens.refreshToken)
    }
}