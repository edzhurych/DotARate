package com.ez.dotarate

import android.content.Context
import com.ez.core_di.DaggerDiComponent
import com.ez.core_di.DiComponent
import com.ez.dotarate.di.AppComponent
import com.ez.dotarate.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.DispatchingAndroidInjector
import javax.inject.Inject


class App : DaggerApplication() {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(this, provideDiComponent())
    }

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
        DaggerAppComponent.factory().create(this, provideDiComponent())
    }

    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> = DaggerAppComponent.factory().create(this, provideDiComponent())

    private fun provideDiComponent(): DiComponent =
        DaggerDiComponent.factory().create(this)
}