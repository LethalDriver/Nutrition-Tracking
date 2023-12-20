package com.mwdziak.fitness_mobile_client.auth

import android.util.Patterns

class Validator {
    fun isNotBlank(input: String): Boolean {
        return input.isNotBlank()
    }

    fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun isValidPassword(password: String): Boolean {
        return password.length >= 8 && password.contains(Regex("[0-9]")) && containsSpecialCharacter(password)
    }

    fun isValidConfirmPassword(password: String, confirmPassword: String): Boolean {
        return password == confirmPassword
    }

    private fun containsSpecialCharacter(input: String): Boolean {
        val specialCharPattern = Regex("[^A-Za-z0-9 ]")
        return specialCharPattern.containsMatchIn(input)
    }
}