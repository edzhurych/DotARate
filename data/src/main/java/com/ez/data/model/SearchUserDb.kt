package com.ez.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ez.domain.model.SearchUser

@Entity(tableName = "recentSearchUsers")
data class SearchUserDb(
    @field:PrimaryKey
    val account_id: Int,
    val avatarfull: String,
    val last_match_time: String?,
    val personaname: String
) {

    fun toSearchUser() = SearchUser(
        account_id = this.account_id,
        avatarfull = this.avatarfull,
        last_match_time = this.last_match_time,
        personaname = this.personaname,
    )
}

fun SearchUser.toSearchUserDb() = SearchUserDb(
    account_id = this.account_id,
    avatarfull = this.avatarfull,
    last_match_time = this.last_match_time,
    personaname = this.personaname,
)

fun List<SearchUserDb>.toSearchUsers() = MutableList(size) { index ->
    this[index].toSearchUser()
}

fun List<SearchUser>.toSearchUsersDb() = List(size) { index ->
    this[index].toSearchUserDb()
}