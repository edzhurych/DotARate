package com.ez.dotarate.view.upcominggames

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import androidx.paging.PagingData
import com.ez.data.di.upcomingGamesByLeagueId
import com.ez.data.di.upcomingGamesId
import com.ez.domain.model.UpcomingGame
import com.ez.dotarate.Log
import com.ez.dotarate.customclasses.UpcomingGamesDataLoaded
import com.ez.dotarate.view.BaseViewModel
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named


class UpcomingGamesViewModel(
    application: Application,
    val isDataLoaded: UpcomingGamesDataLoaded,
) : BaseViewModel(application) {

    val liveLeagueId = MutableLiveData(0)
    val categories = MutableLiveData(mapOf<Int, String>())

    val liveUpcomingGames: LiveData<PagingData<UpcomingGame>> = liveLeagueId.switchMap { leagueId ->
        Log.d("liveUpcomingGames leagueId = $leagueId")
        if (leagueId == null || leagueId == 0) {
            getKoin().get<LiveData<PagingData<UpcomingGame>>>(qualifier = named(upcomingGamesId))
//                .cachedIn(viewModelScope)
        } else {
            Log.d("liveUpcomingGames else")
            getKoin().get<LiveData<PagingData<UpcomingGame>>>(
                qualifier = named(upcomingGamesByLeagueId),
                parameters = {
                    parametersOf(leagueId)
                }
            )
        }
    }

    override fun onCleared() {
        super.onCleared()
    }
}