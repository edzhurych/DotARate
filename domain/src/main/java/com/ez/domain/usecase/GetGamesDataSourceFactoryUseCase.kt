package com.ez.domain.usecase

import androidx.paging.DataSource
import androidx.paging.PagingSource
import com.ez.domain.model.Game
import kotlinx.coroutines.CoroutineScope

interface GetGamesDataSourceFactoryUseCase {

    operator fun invoke(
        isLocal: Boolean,
        scope: CoroutineScope,
        id32: Int
    ): PagingSource<Int, Game>
}