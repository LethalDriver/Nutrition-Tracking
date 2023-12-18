package com.mwdziak.fitness_mobile_client

import android.app.Application
import com.mwdziak.fitness_mobile_client.koin.httpClientModule

import com.mwdziak.fitness_mobile_client.koin.tokenManagerModule
import com.mwdziak.fitness_mobile_client.koin.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication: Application()  {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@MyApplication)
            modules(listOf(httpClientModule, viewModelModule, tokenManagerModule))
        }
    }

}