package com.ez.domain.usecase

import com.ez.domain.model.SearchUser

interface SaveSearchUsersUseCase {

    suspend operator fun invoke(listSearchUsers: List<SearchUser>)
}