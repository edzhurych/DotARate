package com.ez.dotarate.di

import com.ez.dotarate.adapters.GamesAdapter
import com.ez.dotarate.adapters.SearchUsersAdapter
import com.ez.dotarate.adapters.TopPlayersAdapter
import com.ez.dotarate.adapters.UpcomingGamesAdapter
import dagger.Module
import dagger.Provides

@Module
class AdapterModule {

    @Provides
    internal fun provideGamesAdapter() = GamesAdapter()

    @Provides
    internal fun provideSearchUsersAdapter() = SearchUsersAdapter()
}