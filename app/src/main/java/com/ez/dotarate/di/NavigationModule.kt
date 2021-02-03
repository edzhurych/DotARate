package com.ez.dotarate.di

import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import dagger.Module
import dagger.Provides
import java.util.*
import javax.inject.Singleton

@Module
class NavigationModule {

    @Provides
    @Singleton
    internal fun provideStack() = Stack<String>()

    @Provides
    @Singleton
    internal fun provideLiveNavCOntroller() = MutableLiveData<NavController>()
}