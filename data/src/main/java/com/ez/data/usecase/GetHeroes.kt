package com.ez.data.usecase

import com.ez.domain.model.Hero
import com.ez.domain.repository.OpenDotaRepository
import com.ez.domain.usecase.GetHeroesUseCase

class GetHeroes(
    val repository: OpenDotaRepository
) : GetHeroesUseCase {

    override suspend fun invoke(id32: Int): List<Hero> = repository.fetchHeroes(id32)
}