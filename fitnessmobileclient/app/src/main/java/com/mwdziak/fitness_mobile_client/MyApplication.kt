package com.mwdziak.fitness_mobile_client

import android.app.Application
import com.mwdziak.fitness_mobile_client.utils.httpClientModule
import com.mwdziak.fitness_mobile_client.utils.serviceModule
import com.mwdziak.fitness_mobile_client.utils.viewModelModule
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