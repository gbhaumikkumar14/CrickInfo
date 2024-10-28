package com.example.domain.usecase

import com.example.common.Result
import com.example.domain.model.DomainMatchInfo

interface GetMatchInfoUseCase {
    suspend fun getMatchInfo(matchId: String): Result<DomainMatchInfo>
}