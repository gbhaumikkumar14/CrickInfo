package com.example.data.remote.datasource

import com.example.common.Result
import com.example.domain.model.DomainCurrentMatch
import com.example.domain.model.DomainMatchInfo

interface RemoteDataSource {
    suspend fun getCurrentMatches(apiKey: String): Result<List<DomainCurrentMatch>>
    suspend fun getMatchInfo(apiKey: String, matchId: String): Result<DomainMatchInfo>
}