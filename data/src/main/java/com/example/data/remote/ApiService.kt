package com.example.data.remote

import com.example.data.model.CurrentMatchesResponseDto
import com.example.data.model.MatchInfoResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("currentMatches")
    suspend fun getCurrentMatches(
        @Query("apikey") apiKey: String,
        @Query("offset") offset: Int = 0
    ): Response<CurrentMatchesResponseDto>

    @GET("match_info")
    suspend fun getMatchInfo(
        @Query("apikey") apiKey: String,
        @Query("id") matchId: String
    ): Response<MatchInfoResponseDto>
}