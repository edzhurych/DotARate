package com.ez.domain.usecase

import androidx.paging.DataSource
import com.ez.domain.model.SearchUser

interface GetRecentSearchUsersUseCase {

    suspend operator fun invoke(): DataSource.Factory<Int, SearchUser>
}