package com.ez.dotarate.view.upcominggames

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.paging.PagingData
import com.ez.data.di.FlowPagedDataUpcomingGames
import com.ez.data.di.upcomingGamesByLeagueId
import com.ez.data.di.upcomingGamesId
import com.ez.domain.model.UpcomingGame
import com.ez.domain.repository.PandaScoreRepository
import com.ez.dotarate.Log
import com.ez.dotarate.customclasses.UpcomingGamesDataLoaded
import com.ez.dotarate.view.BaseViewModel
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope


class UpcomingGamesViewModel(
    application: Application,
    private val repository: PandaScoreRepository,
    val isDataLoaded: UpcomingGamesDataLoaded,
) : BaseViewModel(application) {

    private val upcomingGamesScope: Scope =
        getKoin().getOrCreateScope<FlowPagedDataUpcomingGames>(upcomingGamesId)

    val liveLeagueId = MutableLiveData(0)
    val categories = MutableLiveData(mapOf<Int, String>())

    val liveUpcomingGames: LiveData<PagingData<UpcomingGame>> = Transformations.switchMap(
        liveLeagueId
    ) { leagueId ->
        Log.d("liveUpcomingGames leagueId = $leagueId")
        if (leagueId == null || leagueId == 0) {
            upcomingGamesScope.get()
        } else {
            Log.d("liveUpcomingGames else")
            getKoin()
                .get(
                    qualifier = named(upcomingGamesByLeagueId),
                    parameters = {
                        parametersOf(leagueId)
                    }
                )
        }
    }

    override fun onCleared() {
        super.onCleared()
        upcomingGamesScope.close()
    }
}