package com.ez.dotarate.model

data class UserResponse(
    val leaderboard_rank: Int?,
    val profile: Profile,
    val rank_tier: Int
)

data class Profile(
    val account_id: Int,
    val avatarfull: String,
    val personaname: String,
    val profileurl: String,
    val steamid: String
)