package com.ez.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ez.data.model.UserIdDb


@Dao
interface UserIdDao {
    // Because this is marked suspend, Room will use it's own dispatcher
    //  to run this query in a main-safe way.
    // That means you can call them directly from Dispatchers.Main.
    @Query("SELECT * FROM userIdDb")
    suspend fun getId(): UserIdDb?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveId(idDb: UserIdDb)

    @Query("DELETE FROM userIdDb")
    suspend fun clearUserId()
}