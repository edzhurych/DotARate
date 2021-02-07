package com.ez.data.di

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.ez.data.dataSource.UpcomingGamesDataSource
import com.ez.domain.model.UpcomingGame
import com.ez.domain.repository.PandaScoreRepository
import dagger.Module
import dagger.Provides

@Module
class UpcomingGamesModule {

    @Provides
    fun providePagedList(repository: PandaScoreRepository): LiveData<PagedList<UpcomingGame>> {
        val config = PagedList.Config.Builder()
            .setPageSize(16)
            .setEnablePlaceholders(false)
            .build()

        return LivePagedListBuilder<Int, UpcomingGame>(
            object : DataSource.Factory<Int, UpcomingGame>() {
                override fun create(): DataSource<Int, UpcomingGame> {
                    return UpcomingGamesDataSource(repository)
                }
            }, config
        ).build()
    }
}