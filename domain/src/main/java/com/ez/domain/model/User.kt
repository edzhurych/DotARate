package com.ez.domain.model


data class User (
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