package com.example.data.mappers

import com.example.data.model.CurrentMatchesResponseDto
import com.example.data.model.MatchInfoResponseDto
import com.example.data.model.ScoreDto
import com.example.data.model.TeamInfoDto
import com.example.domain.model.DomainCurrentMatch
import com.example.domain.model.DomainMatchInfo
import com.example.domain.model.DomainScore
import com.example.domain.model.DomainTeamInfo
import javax.inject.Inject

class MatchDtoToDomainMapper @Inject constructor() {
    fun mapCurrentMatchesToDomainCurrentMatch(currentMatchesResponseDto: CurrentMatchesResponseDto): List<DomainCurrentMatch> =
        currentMatchesResponseDto.data.map { match ->
            with(match) {
                DomainCurrentMatch(
                    date = date ?: "",
                    dateTimeGMT = dateTimeGMT ?: "",
                    hasSquad = hasSquad ?: false,
                    id = id ?: "",
                    matchEnded = matchEnded ?: false,
                    matchStarted = matchStarted ?: false,
                    matchType = matchType ?: "",
                    name = name ?: "",
                    score = mapScoreDtotoDomainScore(listScoreDto = score ?: listOf()),
                    series_id = series_id ?: "",
                    status = status ?: "",
                    teamInfo = mapTeamInfoDtotoDomainTeamInfo(
                        listTeamInfo = teamInfo ?: listOf()
                    ),
                    teams = teams ?: listOf(),
                    venue = venue ?: ""
                )
            }
        }.sortedBy { it.matchStarted && !it.matchEnded }.reversed()

    fun mapMatcheInfoDtoToDomainCurrentMatch(matchInfoResponseDto: MatchInfoResponseDto): DomainMatchInfo =
        with(matchInfoResponseDto.data) {
            DomainMatchInfo(
                date = date ?: "",
                dateTimeGMT = dateTimeGMT ?: "",
                hasSquad = hasSquad ?: false,
                id = id ?: "",
                matchEnded = matchEnded ?: false,
                matchStarted = matchStarted ?: false,
                matchType = matchType ?: "",
                matchWinner = matchWinner ?: "",
                name = name ?: "",
                score = mapScoreDtotoDomainScore(listScoreDto = score ?: listOf()),
                series_id = series_id ?: "",
                status = status ?: "",
                teamInfo = mapTeamInfoDtotoDomainTeamInfo(
                    listTeamInfo = teamInfo ?: listOf()
                ),
                teams = teams ?: listOf(),
                tossChoice = tossChoice ?: "",
                tossWinner = tossWinner ?: "",
                venue = venue ?: ""
            )
        }

    fun mapTeamInfoDtotoDomainTeamInfo(listTeamInfo: List<TeamInfoDto>): List<DomainTeamInfo> =
        listTeamInfo.map {
            with(it) {
                DomainTeamInfo(
                    img = img ?: "",
                    name = name ?: "",
                    shortname = shortname ?: ""
                )
            }
        }

    fun mapScoreDtotoDomainScore(listScoreDto: List<ScoreDto>): List<DomainScore> =
        listScoreDto.map {
            with(it) {
                DomainScore(
                    inning = inning ?: "",
                    o = o,
                    r = r,
                    w = w
                )
            }
        }
}
