package com.ez.dotarate.di

import android.app.Application
import com.ez.dotarate.App
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ApiModule::class,
        ViewModelBinder::class,
        ActivityModule::class,
        DatabaseModule::class,
        NavigationModule::class,
        AdapterModule::class,
        ObservableFieldsModule::class
    ]
)
@Singleton
interface AppComponent : AndroidInjector<App> {

    /* We will call this builder interface from our custom Application class.
     * This will set our applicationBind object to the AppComponent.
     * So inside the AppComponent the applicationBind instance is available.
     * So this applicationBind instance can be accessed by our modules
     * such as ApiModule when needed
     * */
    @Component.Builder
    interface Builder {

        /*
         * При использовании своего билдера, мы можем избежать использования модуля
         * и сразу передавать объект в компонент, используя билдер c помощью аннотации @BindsInstance
         */
        @BindsInstance
        fun applicationBind(application: Application): Builder

        // Как минимум один метод в интерфейсе билдера должен быть всегда - это аналог метода build.
        // Это должен быть метод без аргументов и возвращающий компонент.
        fun build(): AppComponent
    }

    /*
     * This is our custom Application class
     * */
    override fun inject(app: App)
}