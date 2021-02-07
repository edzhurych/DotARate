package com.ez.data.datasource

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.ez.domain.model.UpcomingGame
import com.ez.domain.repository.PandaScoreRepository

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