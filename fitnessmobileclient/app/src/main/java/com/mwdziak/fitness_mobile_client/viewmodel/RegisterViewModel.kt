package com.mwdziak.fitness_mobile_client.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mwdziak.fitness_mobile_client.auth.TokensDTO
import com.mwdziak.fitness_mobile_client.auth.RegistrationRequest
import com.mwdziak.fitness_mobile_client.service.HttpService
import com.mwdziak.fitness_mobile_client.service.TokenManager
import com.mwdziak.fitness_mobile_client.service.Validator
import io.ktor.client.call.receive
import io.ktor.client.request.post
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType

class RegisterViewModel(private val tokenManager: TokenManager,
                        private val validator: Validator,
                        private val httpService: HttpService
): ViewModel() {

    private val email = MutableLiveData<String>("")
    private val password = MutableLiveData<String>("")
    private val confirmPassword = MutableLiveData<String>("")
    private val firstName = MutableLiveData<String>("")
    private val lastName = MutableLiveData<String>("")
    val isAllFieldsValid = MutableLiveData<Boolean>(false)

    fun updateEmail(newEmail: String) {
        email.value = newEmail
        checkFormValidity()
    }

    fun updatePassword(newPassword: String) {
        password.value = newPassword
        checkFormValidity()
    }

    fun updateConfirmPassword(newConfirmPassword: String) {
        confirmPassword.value = newConfirmPassword
        checkFormValidity()
    }

    fun updateFirstName(newFirstName: String) {
        firstName.value = newFirstName
        checkFormValidity()
    }

    fun updateLastName(newLastName: String) {
        lastName.value = newLastName
        checkFormValidity()
    }

    fun isEmailValid(): Boolean {
        return validator.isValidEmail(email.value ?: "")
    }

    fun isPasswordValid(): Boolean {
        return validator.isValidPassword(password.value ?: "")
    }

    fun isFirstNameValid(): Boolean {
        return validator.isNotBlank(firstName.value ?: "")
    }

    fun isLastNameValid(): Boolean {
        return validator.isNotBlank(lastName.value ?: "")
    }

    fun isConfirmPasswordValid(): Boolean {
        return validator.isValidConfirmPassword(password.value ?: "", confirmPassword.value ?: "")
    }

    private fun checkFormValidity() {
        isAllFieldsValid.value = isFirstNameValid() &&
                isLastNameValid() &&
                isEmailValid() &&
                isPasswordValid() &&
                isConfirmPasswordValid()
    }

    suspend fun register(){
        val registrationRequest = RegistrationRequest(email.value ?: "", password.value ?: "",
            firstName.value ?: "", lastName.value ?: "")

        val tokensDTO = httpService.register(registrationRequest)

        tokenManager.saveTokens(tokensDTO.token, tokensDTO.refreshToken)
    }

    suspend fun areGoalsSet(): Boolean {
        return httpService.checkIfGoalsAlreadySet()
    }

}