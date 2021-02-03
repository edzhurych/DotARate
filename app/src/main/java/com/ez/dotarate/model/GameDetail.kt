package com.ez.dotarate.model

data class GameDetail(
    val dire_score: Int,
    val duration: Int,
    val game_mode: Int,
    val lobby_type: Int,
    val players: List<Player>,
    val radiant_score: Int,
    val radiant_win: Boolean,
    val start_time: Int,
    val region: Int
)

data class Player(
    val account_id: Int,
    val assists: Int,
    val backpack_0: Int,
    val backpack_1: Int,
    val backpack_2: Int,
    val deaths: Int,
    val denies: Int,
    val gold: Int,
    val gold_per_min: Int,
    val hero_damage: Int,
    val hero_healing: Int,
    val hero_id: Int,
    val item_0: Int,
    val item_1: Int,
    val item_2: Int,
    val item_3: Int,
    val item_4: Int,
    val item_5: Int,
    val kills: Int,
    val last_hits: Int,
    val leaver_status: Int,
    val level: Int,
    val party_id: Int,
    val party_size: Int,
    val permanent_buffs: List<PermanentBuff>,
    val personaname: String,
    val player_slot: Int,
    val purchase: Purchase,
    val rank_tier: Int,
    val total_gold: Int,
    val tower_damage: Int,
    val xp_per_min: Int
)

data class PermanentBuff(
    val permanent_buff: Int,
    val stack_count: Int
)

data class Purchase(
    val ward_observer: Int?,
    val ward_sentry: Int?,
    val dust: Int?,
    val smoke_of_deceit: Int?,
    val gem: Int?
)
