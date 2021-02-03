package com.ez.dotarate.database

import androidx.paging.DataSource
import androidx.room.*
import kotlinx.coroutines.CompletableJob


@Dao
interface GameDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun saveGames(listGames: ArrayList<Game>): List<Long>

    @Query("SELECT * FROM games ORDER BY match_id DESC")
    fun getGames(): DataSource.Factory<Int, Game>

    @Query("DELETE FROM games")
    suspend fun deleteGames()
}