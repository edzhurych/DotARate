package com.ez.dotarate.di

import com.ez.dotarate.customclasses.UpcomingGamesDataLoaded
import org.koin.dsl.module


val observableFieldsModule = module {

    factory { UpcomingGamesDataLoaded(false) }
}