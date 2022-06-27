package com.ez.data.repository

import android.util.Log
import androidx.paging.DataSource
import com.ez.data.db.AppDatabase
import com.ez.data.model.toGamesDb
import com.ez.data.model.toHeroesDb
import com.ez.data.model.toSearchUsersDb
import com.ez.data.network.ServerApi
import com.ez.domain.model.Game
import com.ez.domain.model.GameDetail
import com.ez.domain.model.Hero
import com.ez.domain.model.SearchUser
import com.ez.domain.repository.OpenDotaRepository


class OpenDotaRepositoryImpl(
    private val api: ServerApi,
    private val db: AppDatabase
) : OpenDotaRepository {

    /**
     * Database function
     */
    override fun getGamesDataSourceFactory() = db.gameDao().getGames().map { it.toGame() }

    /**
     * Database function
     */
    override fun getHeroesDataSourceFactory(): DataSource.Factory<Int, Hero> =
        db.heroDao().getHeroes().map { it.toHero() }

    /**
     * Database function
     */
    override suspend fun saveGames(listGames: List<Game>): List<Long> =
        db.gameDao().saveGames(listGames.toGamesDb())

    /**
     * Database function
     */
    override suspend fun saveHeroes(listHeroes: List<Hero>) =
        db.heroDao().insertHeroes(listHeroes.toHeroesDb())

    /**
     * Database function
     */
    override suspend fun insertSearchUsers(listSearchUsers: List<SearchUser>) =
        db.searchUserDao().insertLastUsersAndDeleteRecent(listSearchUsers.toSearchUsersDb())

    /**
     * Database function
     */
    override fun getRecentSearchUsers() =
        db.searchUserDao().getRecentSearchUsers().map { it.toSearchUser() }

    /**
     * GET request.
     * Receive Game Response that contains 100 matches by default
     */
    override suspend fun fetchMatches(id32: Int): List<Game> {
        Log.d("MyLogs", "ПОШЁЛ ЗАПРОС НА ПРОШЕДШИЕ ИГРЫ")
        val response = api.getGames(id32)

        return if (response.isSuccessful) {
            response.body() ?: emptyList()
        } else emptyList()
    }

    /**
     * GET request.
     */
    override suspend fun fetchGameDetail(id: Long): GameDetail? {
        Log.d("MyLogs", "ПОШЁЛ ЗАПРОС НА КОНКРЕТНУЮ ИГРУ")
        val response = api.getGameDetail(id)

        return if (response.isSuccessful) {
            response.body()
        } else null
    }

    /**
     * GET request.
     */
    override suspend fun fetchHeroes(id32: Int): List<Hero> {
        Log.d("MyLogs", "ПОШЁЛ ЗАПРОС НА ГЕРОЕВ")
        val response = api.fetchHeroes(id32)

        return if (response.isSuccessful) {
            response.body() ?: emptyList()
        } else emptyList()
    }

    /**
     * GET request.
     * Receive Game Response that contains 16 games
     * @param loadPosition = позиция, с которой требуется загружать игры
     */
    override suspend fun fetchMatches(
        id32: Int,
        loadPosition: Int,
        limitSize: Int
    ): List<Game> {
        Log.d("MyLogs", "ПОШЁЛ ЗАПРОС НА ПРОШЕДШИЕ ИГРЫ С ПОЗИЦИИ = $loadPosition")
        val response = api.fetchGames(id = id32, loadPosition = loadPosition, limit = limitSize)

        return if (response.isSuccessful) {
            response.body() ?: emptyList()
        } else emptyList()
    }

    /**
     * GET request.
     * Receive User by name Response
     */
    override suspend fun searchUsersByName(name: String): List<SearchUser> {
        Log.d("MyLogs", "ПОШЁЛ ЗАПРОС НА ИГРОКОВ ПО ИМЕНИ")
        val response = api.searchUsersByName(name)

        return if (response.isSuccessful) {
            response.body() ?: emptyList()
        } else emptyList()
    }

    /**
     * GET request.
     * Receive Top Players Response
     */
    override suspend fun fetchTopPlayers(): List<SearchUser> {
        Log.d("MyLogs", "ПОШЁЛ ЗАПРОС НА ТОПОВЫХ ИГРОКОВ")
        val response = api.getTopPlayers()

        return if (response.isSuccessful) {
            response.body() ?: emptyList()
        } else emptyList()
    }
}