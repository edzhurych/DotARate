package com.ez.domain.usecase

import com.ez.domain.model.GameDetail

interface GetGameDetailUseCase {

    suspend operator fun invoke(id: Long): GameDetail?
}