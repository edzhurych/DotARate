package com.ez.domain.repository

import androidx.paging.PagingSource
import com.ez.domain.model.Game
import com.ez.domain.model.GameDetail
import com.ez.domain.model.Hero
import com.ez.domain.model.SearchUser

interface OpenDotaRepository {
    // Database
    suspend fun saveGames(listGames: List<Game>): List<Long>
    fun getGamesDataSourceFactory(): PagingSource<Int, Game>

    suspend fun saveHeroes(listHeroes: List<Hero>): List<Long>
    fun getHeroesDataSourceFactory(): PagingSource<Int, Hero>

    suspend fun insertSearchUsers(listSearchUsers: List<SearchUser>)
    fun getRecentSearchUsers(): PagingSource<Int, SearchUser>

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