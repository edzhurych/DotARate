package com.ez.dotarate.di

import com.ez.dotarate.adapters.GamesAdapter
import com.ez.dotarate.adapters.SearchUsersAdapter
import org.koin.dsl.module

val koinAdapterModule = module {

    factory { GamesAdapter() }

    factory { SearchUsersAdapter() }
}