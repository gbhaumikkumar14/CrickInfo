package com.example.domain.usecase

import com.example.common.Result
import com.example.domain.model.DomainCurrentMatch
import com.example.domain.repository.CrickInfoRepository
import javax.inject.Inject

class GetCurrentMatchesUseCaseImpl @Inject constructor(
    private val repo: CrickInfoRepository
): GetCurrentMatchesUseCase {
    override suspend fun getCurrentMatches(): Result<List<DomainCurrentMatch>> {
        return repo.getCurrentMatches()
    }
}