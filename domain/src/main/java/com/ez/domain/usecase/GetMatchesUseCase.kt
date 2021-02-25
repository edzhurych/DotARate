package com.ez.domain.usecase

import com.ez.domain.model.Game

interface GetMatchesUseCase {

    suspend operator fun invoke(id32: Int): List<Game>
}