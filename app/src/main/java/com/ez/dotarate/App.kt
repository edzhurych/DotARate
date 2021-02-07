package com.ez.dotarate

import android.app.Application
import android.content.Context
import com.ez.infrastructure.di.apiModule
import com.ez.infrastructure.di.databaseModule
import com.ez.infrastructure.di.upcomingGamesModule
import com.ez.dotarate.di.koinAdapterModule
import com.ez.dotarate.di.koinNavigationModule
import com.ez.dotarate.di.observableFieldsModule
import com.ez.dotarate.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class App : Application() {

    private val koinModules = listOf(
        koinAdapterModule,
        koinNavigationModule,
        observableFieldsModule,
        viewModelModule,
        apiModule,
        databaseModule,
        upcomingGamesModule,
    )

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