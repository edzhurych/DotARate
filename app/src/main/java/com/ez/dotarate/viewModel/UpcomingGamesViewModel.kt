package com.ez.dotarate.viewModel

import android.app.Application
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.ez.domain.model.UpcomingGame
import com.ez.domain.repository.PandaScoreRepository
import javax.inject.Inject

class UpcomingGamesViewModel
@Inject constructor(
    application: Application,
    private val repository: PandaScoreRepository,
    val isDataLoaded: ObservableBoolean,
    val liveUpcomingGames: LiveData<PagedList<UpcomingGame>>
) : AndroidViewModel(application) {

}