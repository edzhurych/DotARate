package com.ez.data.datasource

import androidx.paging.PositionalDataSource
import com.ez.data.model.SearchUserDb

class TopPlayersDataSource(private val listTopPlayers: ArrayList<SearchUserDb>) :
    PositionalDataSource<SearchUserDb>() {

    /**
     * Первоначальная загрузка данных
     */
    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<SearchUserDb>) {
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
    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<SearchUserDb>) {
        if (listTopPlayers.isNotEmpty()) {
            val result = listTopPlayers.subList(
                fromIndex = params.startPosition,
                toIndex = (params.startPosition + params.loadSize)
            )
            callback.onResult(result)
        }
    }
}