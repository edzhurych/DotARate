package com.ez.data.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ez.domain.model.Game


@Dao
interface GameDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun saveGames(listGames: List<Game>): List<Long>

    @Query("SELECT * FROM games ORDER BY match_id DESC")
    fun getGames(): PagingSource<Int, Game>

    @Query("DELETE FROM games")
    suspend fun deleteGames()
}