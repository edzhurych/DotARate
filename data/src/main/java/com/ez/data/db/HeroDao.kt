package com.ez.data.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ez.domain.model.Hero

@Dao
interface HeroDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertHeroes(list: List<Hero>): List<Long>

    @Query("SELECT * FROM heroes ORDER BY games DESC")
    fun getHeroes(): PagingSource<Int, Hero>

    @Query("DELETE FROM heroes")
    suspend fun deleteHeroes()
}