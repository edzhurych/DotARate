package com.ez.data.usecase

import androidx.paging.DataSource
import com.ez.domain.model.SearchUser
import com.ez.domain.repository.OpenDotaRepository
import com.ez.domain.usecase.GetRecentSearchUsersUseCase

class GetRecentSearchUsers(
    val repository: OpenDotaRepository
) : GetRecentSearchUsersUseCase {

    override suspend fun invoke(): DataSource.Factory<Int, SearchUser> = repository.getRecentSearchUsers()
}