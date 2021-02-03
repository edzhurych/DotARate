package com.ez.dotarate.viewModel

import android.app.Application
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.ez.dotarate.dataSource.UpcomingGamesDataSource
import com.ez.dotarate.model.UpcomingGame
import com.ez.dotarate.model.repository.PandaScoreRepository
import javax.inject.Inject

class UpcomingGamesViewModel
@Inject constructor(
    application: Application,
    private val repository: PandaScoreRepository,
    val isDataLoaded: ObservableBoolean
) : AndroidViewModel(application) {

    val liveUpcomingGames: LiveData<PagedList<UpcomingGame>> by lazy {
        val config = PagedList.Config.Builder()
            .setPageSize(16)
            .setEnablePlaceholders(false)
            .build()

        LivePagedListBuilder<Int, UpcomingGame>(
            object : DataSource.Factory<Int, UpcomingGame>() {
                override fun create(): DataSource<Int, UpcomingGame> {
                    return UpcomingGamesDataSource(repository)
                }
            }, config
        ).build()
    }
}