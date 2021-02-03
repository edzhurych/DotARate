package com.ez.dotarate.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    fun getUser(): LiveData<User>

    @Query("SELECT name FROM user")
    fun getUserName(): LiveData<UserName?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveUser(user: User)

    @Query("DELETE FROM user")
    suspend fun clearUser()
}