package com.ez.dotarate.viewmodel

import android.app.Application
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.ez.domain.model.UpcomingGame
import com.ez.domain.repository.PandaScoreRepository
import com.ez.dotarate.customclasses.UpcomingGamesDataLoaded


class UpcomingGamesViewModel constructor(
    application: Application,
    private val repository: PandaScoreRepository,
    val isDataLoaded: UpcomingGamesDataLoaded,
    val liveUpcomingGames: LiveData<PagedList<UpcomingGame>>
) : AndroidViewModel(application) {

}