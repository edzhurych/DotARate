package com.ez.data.db

import androidx.paging.DataSource
import androidx.room.*
import com.ez.data.model.HeroDb

@Dao
interface HeroDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertHeroes(list: List<HeroDb>): List<Long>

    @Query("SELECT * FROM heroes ORDER BY games DESC")
    fun getHeroes(): DataSource.Factory<Int, HeroDb>

    @Query("DELETE FROM heroes")
    suspend fun deleteHeroes()
}