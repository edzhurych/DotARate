package com.ez.data.db

import androidx.paging.DataSource
import androidx.room.*
import com.ez.data.model.SearchUserDb

@Dao
abstract class SearchUserDao {

    @Transaction
    open suspend fun insertLastUsersAndDeleteRecent(listSearchUserDbs: List<SearchUserDb>) {
        deleteRecentUsers()
        insertLastUsers(listSearchUserDbs)
    }

    @Insert
    abstract suspend fun insertLastUsers(listSearchUserDbs: List<SearchUserDb>)

    @Query("DELETE FROM recentSearchUsers")
    abstract suspend fun deleteRecentUsers()

    @Query("SELECT * FROM recentSearchUsers")
    abstract fun getRecentSearchUsers(): DataSource.Factory<Int, SearchUserDb>
}