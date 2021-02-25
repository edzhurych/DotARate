package com.ez.data.usecase

import com.ez.domain.model.SearchUser
import com.ez.domain.repository.OpenDotaRepository
import com.ez.domain.usecase.GetUsersByNameUseCase

class GetUsersByName(
    val repository: OpenDotaRepository
) : GetUsersByNameUseCase {

    override suspend fun invoke(name: String): List<SearchUser> = repository.searchUsersByName(name)
}