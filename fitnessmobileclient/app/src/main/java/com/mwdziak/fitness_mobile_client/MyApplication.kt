package com.mwdziak.fitness_mobile_client

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.mwdziak.fitness_mobile_client.koin.httpClientModule
import com.mwdziak.fitness_mobile_client.koin.serviceModule
import com.mwdziak.fitness_mobile_client.koin.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication: Application()  {
    override fun onCreate() {
        startKoin{
            androidContext(this@MyApplication)
            modules(listOf(httpClientModule, viewModelModule, serviceModule))
        }
        super.onCreate()
    }

}