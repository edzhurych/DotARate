package com.ez.domain.repository

import com.ez.domain.model.Team
import com.ez.domain.model.UpcomingGame

interface PandaScoreRepository {
    suspend fun fetchUpcomingMatches(page: Int, loadSize: Int): List<UpcomingGame>
    suspend fun fetchUpcomingMatchesByLeague(leagueId: Int, page: Int, loadSize: Int): List<UpcomingGame>
    suspend fun fetchTeamById(teamId: Int): Team
}