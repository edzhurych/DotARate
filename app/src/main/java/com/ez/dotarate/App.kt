package com.ez.dotarate

import android.content.Context
import com.ez.dotarate.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.DispatchingAndroidInjector
import javax.inject.Inject


class App : DaggerApplication() {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

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
        DaggerAppComponent.builder()
            .applicationBind(this)
            .build()
            .inject(this)
    }

    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> = DaggerAppComponent.builder()
        .applicationBind(this)
        .build()
}