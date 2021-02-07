package com.ez.infrastructure.di

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.ez.data.datasource.UpcomingGamesDataSource
import com.ez.domain.model.UpcomingGame
import org.koin.dsl.module


val upcomingGamesModule = module {

    factory<LiveData<PagedList<UpcomingGame>>> {
        val config = PagedList.Config.Builder()
            .setPageSize(16)
            .setEnablePlaceholders(false)
            .build()

        LivePagedListBuilder<Int, UpcomingGame>(
            object : DataSource.Factory<Int, UpcomingGame>() {
                override fun create(): DataSource<Int, UpcomingGame> {
                    return UpcomingGamesDataSource(get())
                }
            }, config
        ).build()
    }
}