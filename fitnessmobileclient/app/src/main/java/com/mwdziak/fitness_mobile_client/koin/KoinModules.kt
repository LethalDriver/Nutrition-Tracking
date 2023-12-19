package com.mwdziak.fitness_mobile_client.koin

import com.mwdziak.fitness_mobile_client.auth.TokenManager
import com.mwdziak.fitness_mobile_client.viewmodel.LoginViewModel
import com.mwdziak.fitness_mobile_client.viewmodel.RegisterViewModel
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
import org.koin.core.scope.get


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

val viewModelModule = module {
    viewModel { LoginViewModel(get(named("noAuthHttpClient")), get()) }
    viewModel { RegisterViewModel(get(named("noAuthHttpClient")), get()) }
}
val tokenManagerModule = module {
    single { TokenManager(androidContext()) }
}