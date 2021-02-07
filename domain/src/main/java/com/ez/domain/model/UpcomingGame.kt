package com.ez.domain.model

data class UpcomingGame(
    val begin_at: String?, // 2021-01-18T14:00:00Z
    val name: String,
    val number_of_games: Int,
    val league: League,
    val opponents: ArrayList<Opponent>?
)

data class Opponent(
    val opponent: OpponentX
)

data class OpponentX(
    val id: Int,
    val acronym: String?,
    val image_url: String,
    val location: String?,
    val modified_at: String, // "2020-12-07T11:13:46Z"
    val name: String
)

data class League(
    val id: Int,
    // Имя турнира. Например: "DreamLeague"
    val name: String,
    val image_url: String
)