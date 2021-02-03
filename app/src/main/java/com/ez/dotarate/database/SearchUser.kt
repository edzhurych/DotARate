package com.ez.dotarate.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recentSearchUsers")
data class SearchUser(
    @field:PrimaryKey
    val account_id: Int,
    val avatarfull: String,
    val last_match_time: String?,
    val personaname: String
)