package com.ez.domain.usecase

import androidx.paging.DataSource
import androidx.paging.PagingSource
import com.ez.domain.model.SearchUser

interface GetRecentSearchUsersUseCase {

    suspend operator fun invoke(): PagingSource<Int, SearchUser>
}