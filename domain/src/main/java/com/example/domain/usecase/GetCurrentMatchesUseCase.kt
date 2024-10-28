package com.example.domain.usecase

import com.example.common.Result
import com.example.domain.model.DomainCurrentMatch

interface GetCurrentMatchesUseCase {
    suspend fun getCurrentMatches(): Result<List<DomainCurrentMatch>>
}