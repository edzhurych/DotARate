package com.ez.domain.usecase

import androidx.paging.PagingSource
import com.ez.domain.model.Hero
import kotlinx.coroutines.CoroutineScope

interface GetHeroesDataSourceFactoryUseCase {

    operator fun invoke(
        isLocal: Boolean,
        scope: CoroutineScope,
        id32: Int
    ): PagingSource<Int, Hero>
}