package com.ez.dotarate.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface UserIdDao {
    // Because this is marked suspend, Room will use it's own dispatcher
    //  to run this query in a main-safe way.
    // That means you can call them directly from Dispatchers.Main.
    @Query("SELECT * FROM userid")
    suspend fun getId(): UserId

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveId(id: UserId)

    @Query("DELETE FROM userid")
    suspend fun clearUserId()
}