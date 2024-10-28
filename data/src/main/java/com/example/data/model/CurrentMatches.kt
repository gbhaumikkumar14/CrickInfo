package com.example.data.model

data class CurrentMatchesResponseDto(
    val apikey: String?,
    val `data`: List<CurrentMatchesDto>,
    val status: String?
)

data class CurrentMatchesDto(
    val bbbEnabled: Boolean?,
    val date: String?,
    val dateTimeGMT: String?,
    val fantasyEnabled: Boolean?,
    val hasSquad: Boolean?,
    val id: String?,
    val matchEnded: Boolean?,
    val matchStarted: Boolean?,
    val matchType: String?,
    val name: String?,
    val score: List<ScoreDto>?,
    val series_id: String?,
    val status: String?,
    val teamInfo: List<TeamInfoDto>?,
    val teams: List<String>?,
    val venue: String?
)



