package com.mwdziak.fitness_mobile_client

import android.app.Application
import com.mwdziak.fitness_mobile_client.koin.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication: Application()  {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@MyApplication)
            modules(listOf(networkModule))
        }
    }

}