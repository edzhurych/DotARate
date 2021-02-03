package com.ez.dotarate.viewModel

import android.app.Application
import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.ez.dotarate.customClasses.Event
import com.ez.dotarate.database.Game
import com.ez.dotarate.model.repository.OpenDotaRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject


class GamesViewModel @Inject
constructor(
    application: Application, private val repository: OpenDotaRepositoryImpl
) : AndroidViewModel(application) {

    var id32: Int = 0
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
            repository.getGamesDataSourceFactory(
                isLocal = isLocal,
                scope = viewModelScope,
                id32 = id32
            ), config
        ).build()
    }

    fun getGames(id32: Int) {
        viewModelScope.launch {
            try {
                val listGames = withContext(Dispatchers.IO) {
                    repository.getMatches(id32)
                }
                if (listGames.isNotEmpty()) {
                    repository.saveGames(listGames)
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