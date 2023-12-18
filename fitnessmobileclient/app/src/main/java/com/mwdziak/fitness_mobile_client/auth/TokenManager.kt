package com.mwdziak.fitness_mobile_client.auth

import android.content.Context
import android.content.SharedPreferences

class TokenManager(context: Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("TokenPrefs", Context.MODE_PRIVATE)

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
}