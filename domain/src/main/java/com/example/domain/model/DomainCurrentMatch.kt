package com.example.domain.model

data class DomainCurrentMatch(
    val date: String,
    val dateTimeGMT: String,
    val hasSquad: Boolean,
    val id: String,
    val matchEnded: Boolean,
    val matchStarted: Boolean,
    val matchType: String,
    val name: String,
    val score: List<DomainScore>,
    val series_id: String,
    val status: String,
    val teamInfo: List<DomainTeamInfo>,
    val teams: List<String>,
    val venue: String
)
