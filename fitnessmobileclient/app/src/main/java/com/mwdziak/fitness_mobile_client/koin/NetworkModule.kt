package com.mwdziak.fitness_mobile_client.koin

import io.ktor.client.HttpClient
import io.ktor.client.features.auth.Auth
import io.ktor.client.features.auth.providers.bearer
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import org.koin.dsl.module

val networkModule = module {
    single {
        HttpClient {
            install(JsonFeature) {
                serializer = KotlinxSerializer()
            }
            install(Auth) {
                bearer {
                    loadTokens {
                        // Here you should return your JWT token
                        // You can get it from shared preferences or any other storage
                        "your_jwt_token"
                    }
                    refreshTokens {
                        // Here you should implement token refreshing logic
                        // and return new token
                        "new_jwt_token"
                    }
                }
            }
        }
    }
}