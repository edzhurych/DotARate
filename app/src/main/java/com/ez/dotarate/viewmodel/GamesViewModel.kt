package com.ez.dotarate.viewmodel

import android.app.Application
import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.ez.domain.model.Game
import com.ez.domain.usecase.GetGamesDataSourceFactoryUseCase
import com.ez.domain.usecase.GetMatchesUseCase
import com.ez.domain.usecase.SaveGamesUseCase
import com.ez.dotarate.customclasses.Event
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

    val liveGame: LiveData<PagedList<Game>> by lazy {
        val config = PagedList.Config.Builder()
            .setPageSize(16)
            .setEnablePlaceholders(false)
            .setPrefetchDistance(10) // Количество до конца списка, при достижении которого происходит следующая подгрузка данных
            .build()

        Log.d("MyLogs", "isLocal = $isLocal")

        LivePagedListBuilder<Int, Game>(
            getGamesDataSourceFactory(
                isLocal = isLocal,
                scope = viewModelScope,
                id32 = id32 ?: 0
            ), config
        ).build()
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