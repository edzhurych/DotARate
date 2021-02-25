package com.ez.data.usecase

import androidx.paging.DataSource
import com.ez.data.datasource.HeroesDataSource
import com.ez.domain.model.Hero
import com.ez.domain.repository.OpenDotaRepository
import com.ez.domain.usecase.GetHeroesDataSourceFactoryUseCase
import kotlinx.coroutines.CoroutineScope

class GetHeroesDataSourceFactory(
    val repository: OpenDotaRepository
) : GetHeroesDataSourceFactoryUseCase {

    override fun invoke(
        isLocal: Boolean,
        scope: CoroutineScope,
        id32: Int
    ): DataSource.Factory<Int, Hero> {
        return if (isLocal) repository.getHeroesDataSourceFactory()
        else createRemoteHeroesDataSourceFactory(scope, id32)
    }

    private fun createRemoteHeroesDataSourceFactory(
        scope: CoroutineScope,
        id32: Int
    ): DataSource.Factory<Int, Hero> = object : DataSource.Factory<Int, Hero>() {
        override fun create(): DataSource<Int, Hero> {
            return HeroesDataSource(
                scope = scope,
                repository = repository,
                id32 = id32
            )
        }
    }
}