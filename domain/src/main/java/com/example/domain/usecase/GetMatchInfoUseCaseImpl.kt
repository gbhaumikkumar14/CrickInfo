package com.example.domain.usecase

import com.example.common.Result
import com.example.domain.model.DomainMatchInfo
import com.example.domain.repository.CrickInfoRepository
import javax.inject.Inject

class GetMatchInfoUseCaseImpl @Inject constructor(
    private val repo: CrickInfoRepository
): GetMatchInfoUseCase {
    override suspend fun getMatchInfo(matchId: String): Result<DomainMatchInfo> {
        return repo.getMatchInfo(matchId)
    }
}