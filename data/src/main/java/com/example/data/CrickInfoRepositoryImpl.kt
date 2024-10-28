package com.example.data

import com.example.common.Result
import com.example.data.remote.datasource.RemoteDataSource
import com.example.domain.model.DomainCurrentMatch
import com.example.domain.model.DomainMatchInfo
import com.example.domain.repository.CrickInfoRepository
import javax.inject.Inject

class CrickInfoRepositoryImpl @Inject constructor(
    private val dataSource: RemoteDataSource
): CrickInfoRepository {
    private val apiKey = BuildConfig.API_KEY
    override suspend fun getCurrentMatches(): Result<List<DomainCurrentMatch>> {
        return dataSource.getCurrentMatches(apiKey = apiKey)
    }

    override suspend fun getMatchInfo(matchId: String): Result<DomainMatchInfo> {
        return dataSource.getMatchInfo(apiKey = apiKey, matchId)
    }
}