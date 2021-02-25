package com.ez.data.usecase

import com.ez.domain.model.SearchUser
import com.ez.domain.repository.OpenDotaRepository
import com.ez.domain.usecase.GetTopPlayersUseCase

class GetTopPlayers(
    val repository: OpenDotaRepository
) : GetTopPlayersUseCase {

    override suspend fun invoke(): List<SearchUser> = repository.fetchTopPlayers()
}