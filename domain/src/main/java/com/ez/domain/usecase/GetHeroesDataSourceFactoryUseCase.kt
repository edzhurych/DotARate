package com.ez.domain.usecase

import androidx.paging.DataSource
import com.ez.domain.model.Hero
import kotlinx.coroutines.CoroutineScope

interface GetHeroesDataSourceFactoryUseCase {

    operator fun invoke(
        isLocal: Boolean,
        scope: CoroutineScope,
        id32: Int
    ): DataSource.Factory<Int, Hero>
}