package com.mwdziak.fitness_mobile_client.utils

import android.content.Context
import android.content.SharedPreferences
import com.mwdziak.fitness_mobile_client.activity.MainActivity
import com.mwdziak.fitness_mobile_client.dto.ErrorResponse
import com.mwdziak.fitness_mobile_client.service.HttpService
import com.mwdziak.fitness_mobile_client.service.TokenManager
import com.mwdziak.fitness_mobile_client.service.Validator
import com.mwdziak.fitness_mobile_client.viewmodel.AddMealViewModel
import com.mwdziak.fitness_mobile_client.viewmodel.HistoryViewModel
import com.mwdziak.fitness_mobile_client.viewmodel.IngredientFormViewModel
import com.mwdziak.fitness_mobile_client.viewmodel.LoginViewModel
import com.mwdziak.fitness_mobile_client.viewmodel.MainDashboardViewModel
import com.mwdziak.fitness_mobile_client.viewmodel.RegisterViewModel
import com.mwdziak.fitness_mobile_client.viewmodel.UpdateGoalsViewModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.ClientRequestException
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.kotlinx.json.json
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named




val httpClientModule = module {
    single(named("defaultHttpClient")) {
        HttpClient(Android) {
            install(ContentNegotiation) {
                json()
            }
            install(Auth) {
                bearer {
                    val tokenManager: TokenManager = get()
                    loadTokens {
                        BearerTokens(
                            accessToken = tokenManager.getJwtToken() ?: "",
                            refreshToken = tokenManager.getRefreshToken() ?: ""
                        )
                    }
                    refreshTokens {
                        tokenManager.refreshTokens()
                        BearerTokens(
                            accessToken = tokenManager.getJwtToken() ?: "",
                            refreshToken = tokenManager.getRefreshToken() ?: ""
                        )
                    }

                }
            }
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
            }
        }
    }

    single(named("noAuthHttpClient")) {
        HttpClient(Android) {
            install(ContentNegotiation) {
                json()
            }
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
            }
            expectSuccess = true
            HttpResponseValidator {
                handleResponseExceptionWithRequest { exception, request ->
                    val clientException = exception as? ClientRequestException ?: return@handleResponseExceptionWithRequest
                    val exceptionResponse = clientException.response
                    if (exceptionResponse.status == HttpStatusCode.Unauthorized) {
                        val errorDetails = exceptionResponse.body<ErrorResponse>()
                        when(errorDetails.detail) {
                            "User not found" -> throw Exception("User not found")
                            "Bad credentials" -> throw Exception("Password incorrect")
                            "User already exists" -> throw Exception("User already exists")
                            else -> throw Exception("Unknown error")
                        }
                    }
                }
            }

        }
    }
}

val serviceModule = module {
    single { TokenManager(get(named("noAuthHttpClient")), get())}
    single { Validator() }
    single { HttpService(get(named("noAuthHttpClient")), get(named("defaultHttpClient"))) }
    single<SharedPreferences> {
        androidContext().getSharedPreferences("com.mwdziak.fitness_mobile_client", Context.MODE_PRIVATE)
    }
}

val viewModelModule = module {
    viewModel { LoginViewModel(get(), get()) }
    viewModel { RegisterViewModel(get(), get(), get()) }
    viewModel { UpdateGoalsViewModel(get()) }
    viewModel { MainDashboardViewModel(get(), get()) }
    viewModel { AddMealViewModel(get(), get()) }
    viewModel { IngredientFormViewModel(get(), get()) }
    viewModel { HistoryViewModel(get()) }
}
