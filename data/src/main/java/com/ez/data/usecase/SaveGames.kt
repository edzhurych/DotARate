package com.ez.data.usecase

import com.ez.domain.model.Game
import com.ez.domain.repository.OpenDotaRepository
import com.ez.domain.usecase.SaveGamesUseCase

class SaveGames(
    val repository: OpenDotaRepository
) : SaveGamesUseCase {

    override suspend fun invoke(listGames: List<Game>): List<Long> = repository.saveGames(listGames)
}