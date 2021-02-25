package com.ez.data.usecase

import com.ez.domain.model.Hero
import com.ez.domain.repository.OpenDotaRepository
import com.ez.domain.usecase.SaveHeroesUseCase

class SaveHeroes(
    val repository: OpenDotaRepository
) : SaveHeroesUseCase {

    override suspend fun invoke(listHeroes: List<Hero>): List<Long> = repository.saveHeroes(listHeroes)
}