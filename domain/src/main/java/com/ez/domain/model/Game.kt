package com.ez.domain.model

data class Game(
    val match_id: Long,
    val assists: Int,
    val deaths: Int,
    val duration: Int,
    val game_mode: Int,
    val hero_id: Int,
    val kills: Int,
    val leaver_status: Int,
    val lobby_type: Int,
    val party_size: Int,
    val player_slot: Int,
    val radiant_win: Boolean,
    val skill: Int,
    val start_time: Long,
    val version: Int
)