package com.ez.data.usecase

import com.ez.domain.model.SearchUser
import com.ez.domain.repository.OpenDotaRepository
import com.ez.domain.usecase.SaveSearchUsersUseCase

class SaveSearchUsers(
    val repository: OpenDotaRepository
) : SaveSearchUsersUseCase {

    override suspend fun invoke(listSearchUsers: List<SearchUser>) = repository.insertSearchUsers(listSearchUsers)
}