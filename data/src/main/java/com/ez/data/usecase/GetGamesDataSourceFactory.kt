package com.ez.data.usecase

import androidx.paging.DataSource
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
    ): DataSource.Factory<Int, Game> {
        return if (isLocal) repository.getGamesDataSourceFactory()
        else createRemoteGamesDataSourceFactory(scope, id32)
    }

    private fun createRemoteGamesDataSourceFactory(
        scope: CoroutineScope,
        id32: Int
    ): DataSource.Factory<Int, Game> = object : DataSource.Factory<Int, Game>() {
        override fun create(): DataSource<Int, Game> {
            return GamesDataSource(
                scope = scope,
                repository = repository,
                id32 = id32
            )
        }
    }
}