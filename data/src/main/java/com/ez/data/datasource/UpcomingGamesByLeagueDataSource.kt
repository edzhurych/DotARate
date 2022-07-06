package com.ez.data.datasource

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.ez.domain.model.UpcomingGame
import com.ez.domain.repository.PandaScoreRepository

class UpcomingGamesByLeagueDataSource(
    private val repository: PandaScoreRepository,
    private val leagueId: Int,
) : PageKeyedDataSource<Int, UpcomingGame>() {

    // Первая загрузка данных
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, UpcomingGame>
    ) {
        Log.d("MyLogs", "UpcomingGamesByLeagueDataSource. loadInitial")

        val listUpcomingGames =
            repository.fetchUpcomingMatchesByLeague(leagueId = leagueId, page = 1, loadSize = params.requestedLoadSize)

        if (listUpcomingGames.isNotEmpty()) {
            Log.d(
                "MyLogs",
                "UpcomingGamesByLeagueDataSource. loadInitial. Response = $listUpcomingGames"
            )
            callback.onResult(listUpcomingGames, null, 2)
        }

    }

    // Загрузка следующих порций данных
    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, UpcomingGame>) {
        Log.d("MyLogs", "UpcomingGamesByLeagueDataSource. loadAfter")
        val listUpcomingGames = repository.fetchUpcomingMatchesByLeague(
            leagueId = leagueId,
            page = params.key,
            loadSize = params.requestedLoadSize
        )
        if (listUpcomingGames.isNotEmpty()) {
            Log.d(
                "MyLogs",
                "UpcomingGamesByLeagueDataSource. loadInitial. Response = $listUpcomingGames"
            )
            callback.onResult(listUpcomingGames, (params.key + 1))
        }
    }

    // Вызывается, если указать предыдущий ключ (previousPageKey) в методе loadInitial
    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, UpcomingGame>) {
        Log.d("MyLogs", "UpcomingGamesByLeagueDataSource. loadBefore")
    }
}