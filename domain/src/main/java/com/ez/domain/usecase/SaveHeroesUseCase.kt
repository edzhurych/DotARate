package com.ez.domain.usecase

import com.ez.domain.model.Hero

interface SaveHeroesUseCase {

    suspend operator fun invoke(listHeroes: List<Hero>): List<Long>
}