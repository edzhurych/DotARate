package com.ez.dotarate.di

import androidx.databinding.ObservableBoolean
import dagger.Module
import dagger.Provides

@Module
class ObservableFieldsModule {

    @Provides
    internal fun provideObservableBoolean() = ObservableBoolean(false)
}