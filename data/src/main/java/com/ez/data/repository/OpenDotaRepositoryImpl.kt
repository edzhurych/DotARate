package com.ez.data.repository

import android.util.Log
import androidx.paging.DataSource
import com.ez.data.dataSource.GamesDataSource
import com.ez.data.dataSource.HeroesDataSource
import com.ez.data.model.*
import com.ez.data.network.ServerApi
import com.ez.domain.model.Game
import com.ez.domain.model.GameDetail
import com.ez.domain.model.Hero
import com.ez.domain.model.SearchUser
import com.ez.domain.repository.OpenDotaRepository
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject
import javax.inject.Named


class OpenDotaRepositoryImpl
@Inject constructor(
    @Named("OpendotaApi") private val api: ServerApi,
    private val db: com.ez.data.db.AppDatabase
) : OpenDotaRepository {

    private fun createRemoteGamesDataSource(
        scope: CoroutineScope,
        id32: Int
    ): DataSource.Factory<Int, Game> = object : DataSource.Factory<Int, Game>() {
        override fun create(): DataSource<Int, Game> {
            return GamesDataSource(
                scope = scope,
                repository = this@OpenDotaRepositoryImpl,
                id32 = id32
            )
        }
    }

    private fun createRemoteHeroesDataSource(
        scope: CoroutineScope,
        id32: Int
    ): DataSource.Factory<Int, Hero> = object : DataSource.Factory<Int, Hero>() {
        override fun create(): DataSource<Int, Hero> {
            return HeroesDataSource(
                scope = scope,
                repository = this@OpenDotaRepositoryImpl,
                id32 = id32
            )
        }
    }


    /**
     * @return games DataSource.Factory
     */
    override fun getGamesDataSourceFactory(
        isLocal: Boolean,
        scope: CoroutineScope,
        id32: Int
    ): DataSource.Factory<Int, Game> {
        return if (isLocal) db.gameDao().getGames().map { it.toGame() }
        else createRemoteGamesDataSource(scope, id32)
    }

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
     * @return heroes DataSource.Factory
     */
    override fun getHeroesDataSourceFactory(
        isLocal: Boolean,
        scope: CoroutineScope,
        id32: Int
    ): DataSource.Factory<Int, Hero> {
        return if (isLocal) db.heroDao().getHeroes().map { it.toHero() }
        else createRemoteHeroesDataSource(scope, id32)
    }

    /**
     * GET request.
     * Receive Game Response that contains 100 matches by default
     * We don’t need to call enqueue() and implement callbacks anymore!
     * But notice, now our repo method is suspend too and returns a Response<ArrayList<Game>>.
     */
    override suspend fun getMatches(id32: Int): List<Game> {
        Log.d("MyLogs", "ПОШЁЛ ЗАПРОС НА ПРОШЕДШИЕ ИГРЫ")
        val response = api.getGames(id32)

        return if (response.isSuccessful) {
            response.body() ?: listOf()
        } else listOf()
    }

    /**
     * GET request.
     * Receive detail Game
     * We don’t need to call enqueue() and implement callbacks anymore!
     * But notice, now our repo method is suspend too and returns a Response<GameDetail>.
     */
    override suspend fun getGameDetail(id: Long): GameDetail? {
        Log.d("MyLogs", "ПОШЁЛ ЗАПРОС НА КОНКРЕТНУЮ ИГРУ")
        val response = api.getGameDetail(id)

        return if (response.isSuccessful) {
            response.body()
        } else null
    }

    /**
     * GET request.
     * Receive Heroes
     */
    override suspend fun fetchHeroes(id32: Int): List<Hero> {
        Log.d("MyLogs", "ПОШЁЛ ЗАПРОС НА ГЕРОЕВ")
        val response = api.fetchHeroes(id32)

        return if (response.isSuccessful) {
            response.body() ?: listOf()
        } else listOf()
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
            response.body() ?: listOf()
        } else listOf()
    }

    /**
     * GET request.
     * Receive User by name Response
     */
    override suspend fun searchUsersByName(name: String): List<SearchUser> {
        Log.d("MyLogs", "ПОШЁЛ ЗАПРОС НА ИГРОКОВ ПО ИМЕНИ")
        val response = api.searchUsersByName(name)

        return if (response.isSuccessful) {
            response.body() ?: listOf()
        } else listOf()
    }

    /**
     * GET request.
     * Receive Top Players Response
     */
    override suspend fun getTopPlayers(): List<SearchUser> {
        Log.d("MyLogs", "ПОШЁЛ ЗАПРОС НА ТОПОВЫХ ИГРОКОВ")
        val response = api.getTopPlayers()

        return if (response.isSuccessful) {
            response.body() ?: listOf()
        } else listOf()
    }
}