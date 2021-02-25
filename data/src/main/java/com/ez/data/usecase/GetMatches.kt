package com.ez.data.usecase

import com.ez.domain.model.Game
import com.ez.domain.repository.OpenDotaRepository
import com.ez.domain.usecase.GetMatchesUseCase

class GetMatches(
    val repository: OpenDotaRepository
) : GetMatchesUseCase {

    override suspend fun invoke(id32: Int): List<Game> = repository.fetchMatches(id32)
}