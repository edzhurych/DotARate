package com.ez.domain.repository

import androidx.paging.DataSource
import com.ez.domain.model.Game
import com.ez.domain.model.GameDetail
import com.ez.domain.model.Hero
import com.ez.domain.model.SearchUser
import kotlinx.coroutines.CoroutineScope

interface OpenDotaRepository {
    // Database
    suspend fun saveGames(listGames: List<Game>): List<Long>
    fun getGamesDataSourceFactory(): DataSource.Factory<Int, Game>

    suspend fun saveHeroes(listHeroes: List<Hero>): List<Long>
    fun getHeroesDataSourceFactory(): DataSource.Factory<Int, Hero>

    suspend fun insertSearchUsers(listSearchUsers: List<SearchUser>)
    fun getRecentSearchUsers(): DataSource.Factory<Int, SearchUser>

    // Network
    suspend fun fetchMatches(id32: Int): List<Game>
    suspend fun fetchGameDetail(id: Long): GameDetail?
    suspend fun fetchHeroes(id32: Int): List<Hero>
    suspend fun fetchMatches(
        id32: Int,
        loadPosition: Int,
        limitSize: Int
    ): List<Game>

    suspend fun searchUsersByName(name: String): List<SearchUser>
    suspend fun fetchTopPlayers(): List<SearchUser>
}