package com.example.data.remote.datasource

import com.example.common.Result
import com.example.data.mappers.MatchDtoToDomainMapper
import com.example.data.remote.ApiService
import com.example.domain.model.DomainCurrentMatch
import com.example.domain.model.DomainMatchInfo
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val apiService: ApiService,
    private val mapper: MatchDtoToDomainMapper
): BaseRemoteDataSource(), RemoteDataSource {
    override suspend fun getCurrentMatches(apiKey: String): Result<List<DomainCurrentMatch>> {
        return callAPI({apiService.getCurrentMatches(apiKey)}, mapper::mapCurrentMatchesToDomainCurrentMatch)
    }

    override suspend fun getMatchInfo(apiKey: String, matchId: String): Result<DomainMatchInfo> {
        return callAPI({apiService.getMatchInfo(apiKey, matchId)}, mapper::mapMatcheInfoDtoToDomainCurrentMatch)
    }

}