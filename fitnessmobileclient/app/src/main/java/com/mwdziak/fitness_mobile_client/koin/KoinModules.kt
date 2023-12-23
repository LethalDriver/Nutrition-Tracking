package com.mwdziak.fitness_mobile_client.koin

import com.mwdziak.fitness_mobile_client.service.HttpService
import com.mwdziak.fitness_mobile_client.service.TokenManager
import com.mwdziak.fitness_mobile_client.service.Validator
import com.mwdziak.fitness_mobile_client.viewmodel.LoginViewModel
import com.mwdziak.fitness_mobile_client.viewmodel.RegisterViewModel
import com.mwdziak.fitness_mobile_client.viewmodel.UpdateGoalsViewModel
import io.ktor.client.HttpClient
import io.ktor.client.features.auth.Auth
import io.ktor.client.features.auth.providers.bearer
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import io.ktor.client.features.auth.providers.BearerTokens
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named



val httpClientModule = module {
    single(named("defaultHttpClient")) {
        HttpClient {
            install(JsonFeature) {
                serializer = KotlinxSerializer()
            }
            install(Auth) {
                bearer {
                    val tokenManager: TokenManager = get()
                    loadTokens {
                        BearerTokens(
                            accessToken = tokenManager.getJwtToken()!!,
                            refreshToken = tokenManager.getRefreshToken()!!
                        )
                    }
                    refreshTokens {
                        tokenManager.refreshTokens()
                        BearerTokens(
                            accessToken = tokenManager.getJwtToken()!!,
                            refreshToken = tokenManager.getRefreshToken()!!
                        )
                    }

                }
            }
        }
    }

    single(named("noAuthHttpClient")) {
        HttpClient {
            install(JsonFeature) {
                serializer = KotlinxSerializer()
            }
        }
    }
}

val serviceModule = module {
    single { TokenManager(androidContext(), get(named("noAuthHttpClient"))) }
    single { Validator() }
    single { HttpService(get(named("noAuthHttpClient")), get(named("defaultHttpClient"))) }
}

val viewModelModule = module {
    viewModel { LoginViewModel(get(), get()) }
    viewModel { RegisterViewModel(get(), get(), get()) }
    viewModel { UpdateGoalsViewModel(get(named("defaultHttpClient")), get()) }
}
