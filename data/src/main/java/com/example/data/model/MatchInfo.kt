package com.example.data.model

data class MatchInfoResponseDto(
    val apikey: String?,
    val `data`: MatchInfoDto,
    val status: String?
)

data class MatchInfoDto(
    val bbbEnabled: Boolean?,
    val date: String?,
    val dateTimeGMT: String?,
    val fantasyEnabled: Boolean?,
    val hasSquad: Boolean?,
    val id: String?,
    val matchEnded: Boolean?,
    val matchStarted: Boolean?,
    val matchType: String?,
    val matchWinner: String?,
    val name: String?,
    val score: List<ScoreDto>?,
    val series_id: String?,
    val status: String?,
    val teamInfo: List<TeamInfoDto>?,
    val teams: List<String>?,
    val tossChoice: String?,
    val tossWinner: String?,
    val venue: String?
)