package com.ez.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ez.domain.model.User

@Entity
data class UserDb(
    @PrimaryKey
    var id: Int,
    var leaderboard_rank: Int? = null,
    var name: String? = null,
    var avatarUrl: String? = null,
    var wins: Int? = null,
    var losses: Int? = null,
    var rankId: Int? = null
) {

    fun toUser() = User(
        id = this.id,
        leaderboard_rank = this.leaderboard_rank,
        name = this.name,
        avatarUrl = this.avatarUrl,
        wins = this.wins,
        losses = this.losses,
        rankId = this.rankId,
    )
}

fun User.toUserDb() = UserDb(
    id = this.id ?: 0,
    leaderboard_rank = this.leaderboard_rank,
    name = this.name,
    avatarUrl = this.avatarUrl,
    wins = this.wins,
    losses = this.losses,
    rankId = this.rankId,
)