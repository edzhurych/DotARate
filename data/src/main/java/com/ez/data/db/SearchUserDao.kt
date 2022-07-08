package com.ez.data.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.ez.domain.model.SearchUser

@Dao
abstract class SearchUserDao {

    @Transaction
    open suspend fun insertLastUsersAndDeleteRecent(listSearchUsers: List<SearchUser>) {
        deleteRecentUsers()
        insertLastUsers(listSearchUsers)
    }

    @Insert
    abstract suspend fun insertLastUsers(listSearchUsers: List<SearchUser>)

    @Query("DELETE FROM recentSearchUsers")
    abstract suspend fun deleteRecentUsers()

    @Query("SELECT * FROM recentSearchUsers")
    abstract fun getRecentSearchUsers(): PagingSource<Int, SearchUser>
}