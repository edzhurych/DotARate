package com.ez.dotarate.di

import android.app.Application
import android.app.ApplicationErrorReport
import android.content.Context
import com.ez.core_di.DiComponent
import com.ez.dotarate.App
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ViewModelBinder::class,
        ActivityModule::class,
        NavigationModule::class,
        AdapterModule::class,
        ObservableFieldsModule::class
    ],
    dependencies = [
        DiComponent::class
    ]
)
@AppScope
interface AppComponent : AndroidInjector<App> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Application, appComponent: DiComponent): AppComponent
    }

//    fun getDiComponent(): DiComponent

//    @Component.Builder
//    interface Builder {
//
//        @BindsInstance
//        fun bindApplication(application: Application): Builder
//
//        fun bindDiComponent(diComponent: DiComponent): Builder
//
//        fun build(): AppComponent
//    }

    override fun inject(app: App)
}