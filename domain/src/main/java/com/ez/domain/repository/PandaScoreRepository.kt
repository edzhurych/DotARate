package com.ez.domain.repository

import com.ez.domain.model.Team
import com.ez.domain.model.UpcomingGame

interface PandaScoreRepository {
    fun fetchUpcomingMatches(page: Int, loadSize: Int): ArrayList<UpcomingGame>
    suspend fun fetchTeamById(teamId: Int): Team
}