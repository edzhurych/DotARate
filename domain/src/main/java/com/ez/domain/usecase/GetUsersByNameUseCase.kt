package com.ez.domain.usecase

import com.ez.domain.model.SearchUser

interface GetUsersByNameUseCase {

    suspend operator fun invoke(name: String): List<SearchUser>
}