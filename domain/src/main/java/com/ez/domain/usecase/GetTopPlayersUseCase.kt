package com.ez.domain.usecase

import com.ez.domain.model.SearchUser

interface GetTopPlayersUseCase {

    suspend operator fun invoke(): List<SearchUser>
}