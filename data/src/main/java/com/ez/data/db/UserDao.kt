package com.ez.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ez.data.model.UserDb
import com.ez.domain.model.UserName

@Dao
interface UserDao {

    @Query("SELECT * FROM userDb")
    suspend fun getUser(): UserDb

    @Query("SELECT name FROM userDb")
    fun getUserName(): LiveData<UserName?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveUser(user: UserDb): Long

    @Query("DELETE FROM userDb")
    suspend fun clearUser()
}