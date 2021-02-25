package com.ez.dotarate

import android.app.Application
import android.content.Context
import com.ez.dotarate.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class App : Application() {

    companion object {
        lateinit var instance: App
            private set

        fun applicationContext(): Context {
            return instance.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        startKoin {
            androidContext(this@App)
            modules(koinModules)
        }
    }
}