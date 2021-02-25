package com.ez.domain.usecase

import com.ez.domain.model.Hero

interface GetHeroesUseCase {

    suspend operator fun invoke(id32: Int): List<Hero>
}