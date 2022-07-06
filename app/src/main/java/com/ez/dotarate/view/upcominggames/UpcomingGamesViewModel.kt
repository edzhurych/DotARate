package com.ez.dotarate.view.upcominggames

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.paging.PagedList
import com.ez.data.di.LivePagedListUpcomingGames
import com.ez.data.di.LivePagedListUpcomingGamesByLeague
import com.ez.data.di.upcomingGamesByLeagueId
import com.ez.data.di.upcomingGamesId
import com.ez.domain.model.UpcomingGame
import com.ez.domain.repository.PandaScoreRepository
import com.ez.dotarate.Log
import com.ez.dotarate.customclasses.UpcomingGamesDataLoaded
import com.ez.dotarate.view.BaseViewModel
import org.koin.android.ext.android.inject
import org.koin.core.component.inject
import org.koin.core.parameter.DefinitionParameters
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope


class UpcomingGamesViewModel(
    application: Application,
    private val repository: PandaScoreRepository,
    val isDataLoaded: UpcomingGamesDataLoaded,
) : BaseViewModel(application) {

    private val upcomingGamesScope: Scope =
        getKoin().getOrCreateScope<LivePagedListUpcomingGames>(upcomingGamesId)

    private val upcomingGamesByLeagueScope: Scope by lazy {
        getKoin().getOrCreateScope<LivePagedListUpcomingGamesByLeague>(upcomingGamesByLeagueId)
    }

//    private val upcomingGamesByLeague: LiveData<PagedList<UpcomingGame>> by inject { parametersOf(liveLeagueId.value) }

    val liveLeagueId = MutableLiveData(0)
    val categories = MutableLiveData(mapOf<Int, String>())

    val liveUpcomingGames: LiveData<PagedList<UpcomingGame>> = Transformations.switchMap(
        liveLeagueId
    ) { leagueId ->
        Log.d("liveUpcomingGames leagueId = $leagueId")
        if (leagueId == null || leagueId == 0) {
            upcomingGamesScope.get()
        } else {
            Log.d("liveUpcomingGames else")
            getKoin()
                .get<LiveData<PagedList<UpcomingGame>>>(
                    qualifier = named(upcomingGamesByLeagueId),
                    parameters = {
                        parametersOf(leagueId)
                    }
                )
//            upcomingGamesByLeague
//            MutableLiveData<PagedList<UpcomingGame>>()
        }
    }

    override fun onCleared() {
        super.onCleared()
        upcomingGamesScope.close()
    }
}