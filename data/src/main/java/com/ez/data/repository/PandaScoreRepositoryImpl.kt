package com.ez.data.repository

import com.ez.data.constants.PANDASCORE_TOKEN
import com.ez.domain.model.Team
import com.ez.domain.model.UpcomingGame
import com.ez.data.network.ServerApi
import com.ez.domain.model.Filter
import com.ez.domain.repository.PandaScoreRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PandaScoreRepositoryImpl(
    private val api: ServerApi
) : PandaScoreRepository {

    override suspend fun fetchUpcomingMatches(page: Int, loadSize: Int): List<UpcomingGame> {
        val call = api.fetchUpcomingGames(
            token = PANDASCORE_TOKEN,
            page = page,
            loadSize = loadSize
        )

        val response = withContext(Dispatchers.IO) {
            call.execute()
        }

        return if (response.isSuccessful) {
            response.body() ?: emptyList()
        } else emptyList()
    }

    override suspend fun fetchUpcomingMatchesByLeague(
        leagueId: Int,
        page: Int,
        loadSize: Int
    ): List<UpcomingGame> {
        val call = api.fetchUpcomingGamesByLeague(
            token = PANDASCORE_TOKEN,
            filter = mapOf("league_id" to listOf(leagueId)),
            page = page,
            loadSize = loadSize
        )

        val response = call.execute()

        return if (response.isSuccessful) {
            response.body() ?: emptyList()
        } else emptyList()
    }

    override suspend fun fetchTeamById(teamId: Int): Team {
        val response = api.fetchTeamById(
            token = PANDASCORE_TOKEN,
            id = teamId
        )

        return if (response.isSuccessful) {
            response.body() ?: Team()
        }  else Team()
    }
}