package com.example.domain.repository

import com.example.common.Result
import com.example.domain.model.DomainCurrentMatch
import com.example.domain.model.DomainMatchInfo

interface CrickInfoRepository {
    suspend fun getCurrentMatches(): Result<List<DomainCurrentMatch>>

    suspend fun getMatchInfo(matchId: String): Result<DomainMatchInfo>
}