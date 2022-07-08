package com.ez.data.usecase

import androidx.paging.PagingSource
import com.ez.data.datasource.GamesDataSource
import com.ez.domain.model.Game
import com.ez.domain.repository.OpenDotaRepository
import com.ez.domain.usecase.GetGamesDataSourceFactoryUseCase
import kotlinx.coroutines.CoroutineScope

class GetGamesDataSourceFactory(
    val repository: OpenDotaRepository
) : GetGamesDataSourceFactoryUseCase {

    override fun invoke(
        isLocal: Boolean,
        scope: CoroutineScope,
        id32: Int
    ): PagingSource<Int, Game> {
        return if (isLocal) repository.getGamesDataSourceFactory()
        else createRemoteGamesDataSourceFactory(scope, id32)
    }

    private fun createRemoteGamesDataSourceFactory(
        scope: CoroutineScope,
        id32: Int
    ): PagingSource<Int, Game> =
        GamesDataSource(
            scope = scope,
            repository = repository,
            id32 = id32
        )
}