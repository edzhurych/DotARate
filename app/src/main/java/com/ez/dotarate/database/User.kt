package com.ez.dotarate.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User (
    @field:PrimaryKey
    var id: Int? = null,
    var leaderboard_rank: Int? = null,
    var name: String? = null,
    var avatarUrl: String? = null,
    var wins: Int? = null,
    var losses: Int? = null,
    var rankId: Int? = null
)

data class UserName(
    val name: String
)