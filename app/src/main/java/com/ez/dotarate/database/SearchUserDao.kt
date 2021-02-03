package com.ez.dotarate.database

import androidx.paging.DataSource
import androidx.room.*

@Dao
abstract class SearchUserDao {

    @Transaction
    open suspend fun insertLastUsersAndDeleteRecent(listSearchUsers: ArrayList<SearchUser>) {
        deleteRecentUsers()
        insertLastUsers(listSearchUsers)
    }

    @Insert
    abstract suspend fun insertLastUsers(listSearchUsers: ArrayList<SearchUser>)

    @Query("DELETE FROM recentSearchUsers")
    abstract suspend fun deleteRecentUsers()

    @Query("SELECT * FROM recentSearchUsers")
    abstract fun getRecentSearchUsers(): DataSource.Factory<Int, SearchUser>
}