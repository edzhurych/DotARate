package com.ez.data.db

import androidx.paging.DataSource
import androidx.room.*
import com.ez.data.model.GameDb


@Dao
interface GameDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun saveGames(listGames: List<GameDb>): List<Long>

    @Query("SELECT * FROM games ORDER BY match_id DESC")
    fun getGames(): DataSource.Factory<Int, GameDb>

    @Query("DELETE FROM games")
    suspend fun deleteGames()
}