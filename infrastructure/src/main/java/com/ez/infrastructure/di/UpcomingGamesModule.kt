package com.ez.infrastructure.di

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.ez.data.datasource.UpcomingGamesByLeagueDataSource
import com.ez.data.datasource.UpcomingGamesDataSource
import com.ez.data.di.FlowPagedDataUpcomingGames
import com.ez.data.di.upcomingGamesByLeagueId
import com.ez.domain.model.UpcomingGame
import org.koin.core.qualifier.named
import org.koin.dsl.module


val koinUpcomingGamesModule = module {

    scope<FlowPagedDataUpcomingGames> {
        scoped<LiveData<PagingData<UpcomingGame>>> {
            Pager(
                // Configure how data is loaded by passing additional properties to
                // PagingConfig, such as prefetchDistance.
                PagingConfig(pageSize = 20)
            ) {
                UpcomingGamesDataSource(get())
            }.liveData
        }
    }

    single<LiveData<PagingData<UpcomingGame>>>(named(upcomingGamesByLeagueId)) { params ->
        Log.d("mylogs", "DI. SINGLE. params - $params")

        Pager(
            // Configure how data is loaded by passing additional properties to
            // PagingConfig, such as prefetchDistance.
            PagingConfig(pageSize = 20)
        ) {
            UpcomingGamesByLeagueDataSource(get(), leagueId = params.get())
        }.liveData
    }
}