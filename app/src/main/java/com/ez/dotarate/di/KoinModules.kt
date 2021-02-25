package com.ez.dotarate.di

import com.ez.infrastructure.di.useCasesModule
import com.ez.infrastructure.di.koinApiModule
import com.ez.infrastructure.di.koinDatabaseModule
import com.ez.infrastructure.di.koinUpcomingGamesModule

val koinModules = listOf(
    koinAdapterModule,
    koinNavigationModule,
    koinObservableFieldsModule,
    koinViewModelModule,
    koinApiModule,
    koinDatabaseModule,
    koinUpcomingGamesModule,
    useCasesModule,
)