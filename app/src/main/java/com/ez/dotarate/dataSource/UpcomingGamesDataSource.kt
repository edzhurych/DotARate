package com.ez.dotarate.dataSource

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.ez.dotarate.model.UpcomingGame
import com.ez.dotarate.model.repository.PandaScoreRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UpcomingGamesDataSource(
    private val repository: PandaScoreRepository
) : PageKeyedDataSource<Int, UpcomingGame>() {

    // Первая загрузка данных
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, UpcomingGame>
    ) {
        Log.d("MyLogs", "UpcomingGamesDataSource. loadInitial")

        val listUpcomingGames =
            repository.fetchUpcomingMatches(page = 1, loadSize = params.requestedLoadSize)

        if (listUpcomingGames.isNotEmpty()) {
            Log.d(
                "MyLogs",
                "UpcomingGamesDataSource. loadInitial. Response = $listUpcomingGames"
            )
            callback.onResult(listUpcomingGames, null, 2)
        }

    }

    // Загрузка следующих порций данных
    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, UpcomingGame>) {
        Log.d("MyLogs", "UpcomingGamesDataSource. loadAfter")
        val listUpcomingGames = repository.fetchUpcomingMatches(
                page = params.key,
                loadSize = params.requestedLoadSize
            )
        if (listUpcomingGames.isNotEmpty()) {
            Log.d(
                "MyLogs",
                "UpcomingGamesDataSource. loadInitial. Response = $listUpcomingGames"
            )
            callback.onResult(listUpcomingGames, (params.key + 1))
        }
    }

    // Вызывается, если указать предыдущий ключ (previousPageKey) в методе loadInitial
    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, UpcomingGame>) {
        Log.d("MyLogs", "UpcomingGamesDataSource. loadBefore")
    }
}