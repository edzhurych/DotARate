package com.ez.domain.usecase

import com.ez.domain.model.Game

interface SaveGamesUseCase {

    suspend operator fun invoke(listGames: List<Game>): List<Long>
}