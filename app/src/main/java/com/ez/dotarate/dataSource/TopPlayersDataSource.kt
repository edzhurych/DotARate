package com.ez.dotarate.dataSource

import androidx.paging.PositionalDataSource
import com.ez.dotarate.database.SearchUser

class TopPlayersDataSource(private val listTopPlayers: ArrayList<SearchUser>) :
    PositionalDataSource<SearchUser>() {

    /**
     * Первоначальная загрузка данных
     */
    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<SearchUser>) {
        if (listTopPlayers.isNotEmpty()) {
            val result = listTopPlayers.subList(
                fromIndex = params.requestedStartPosition,
                toIndex = params.requestedLoadSize
            )
            callback.onResult(result, 0)
        }
    }

    /**
     * Подгрузка новой порции данных
     */
    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<SearchUser>) {
        if (listTopPlayers.isNotEmpty()) {
            val result = listTopPlayers.subList(
                fromIndex = params.startPosition,
                toIndex = (params.startPosition + params.loadSize)
            )
            callback.onResult(result)
        }
    }
}