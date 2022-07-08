package com.ez.dotarate.view.games

import android.app.Application
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.ez.domain.model.Game
import com.ez.domain.usecase.GetGamesDataSourceFactoryUseCase
import com.ez.domain.usecase.GetMatchesUseCase
import com.ez.domain.usecase.SaveGamesUseCase
import com.ez.dotarate.customclasses.Event
import com.ez.dotarate.view.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.SocketTimeoutException
import java.net.UnknownHostException


class GamesViewModel(
    application: Application,
    private val getGamesDataSourceFactory: GetGamesDataSourceFactoryUseCase,
    private val getMatches: GetMatchesUseCase,
    private val saveGames: SaveGamesUseCase,
) : BaseViewModel(application) {

    var id32: Int? = null
    var isLocal = false

    val isGamesEmpty = ObservableBoolean(false)
    val isLoaded = ObservableBoolean(false)
    val isDataReceived = ObservableBoolean(false)

    val errorLiveData = MutableLiveData<Event<String>>()

    val liveGame: LiveData<PagingData<Game>> by lazy {
        Pager(
            // Configure how data is loaded by passing additional properties to
            // PagingConfig, such as prefetchDistance.
            PagingConfig(pageSize = 20)
        ) {
            getGamesDataSourceFactory(
                isLocal = isLocal,
                scope = viewModelScope,
                id32 = id32 ?: 0
            )
        }.liveData
    }

    fun getGames(id32: Int) {
        viewModelScope.launch {
            try {
                val listGames = withContext(Dispatchers.IO) {
                    getMatches(id32)
                }
                if (listGames.isNotEmpty()) {
                    saveGames(listGames)
                    isLoaded.set(true)
                } else {
                    isLoaded.set(true)
                }
            } catch (e: UnknownHostException) {
                isLoaded.set(true)
                errorLiveData.value =
                    Event("Отсутствует интернет соединение")
            } catch (e: SocketTimeoutException) {
                isLoaded.set(true)
                errorLiveData.value =
                    Event("Плохое соединение. Попробуйте позже")
            }
        }
    }
}