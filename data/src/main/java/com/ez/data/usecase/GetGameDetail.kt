package com.ez.data.usecase

import com.ez.domain.model.GameDetail
import com.ez.domain.repository.OpenDotaRepository
import com.ez.domain.usecase.GetGameDetailUseCase

class GetGameDetail(
    val repository: OpenDotaRepository
) : GetGameDetailUseCase {

    override suspend fun invoke(id: Long): GameDetail? = repository.fetchGameDetail(id)
}