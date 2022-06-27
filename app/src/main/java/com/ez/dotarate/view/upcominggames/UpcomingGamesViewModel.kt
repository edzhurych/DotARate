package com.ez.dotarate.view.upcominggames

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.ez.data.di.LivePagedListUpcomingGames
import com.ez.data.di.upcomingGamesId
import com.ez.domain.model.UpcomingGame
import com.ez.domain.repository.PandaScoreRepository
import com.ez.dotarate.customclasses.UpcomingGamesDataLoaded
import com.ez.dotarate.view.BaseViewModel
import org.koin.core.scope.Scope


class UpcomingGamesViewModel(
    application: Application,
    private val repository: PandaScoreRepository,
    val isDataLoaded: UpcomingGamesDataLoaded,
) : BaseViewModel(application) {

    private val koinScope: Scope =
        getKoin().getOrCreateScope<LivePagedListUpcomingGames>(upcomingGamesId)
    val liveUpcomingGames: LiveData<PagedList<UpcomingGame>> = koinScope.get()

    override fun onCleared() {
        super.onCleared()
        koinScope.close()
    }
}