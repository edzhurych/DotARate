package com.ez.dotarate.model

data class Team(
    val acronym: String? = null,
    val current_videogame: VideoGame? = null,
    val id: Int? = null,
    val image_url: String? = null,
    val location: String? = null,
    val modified_at: String? = null, // "2019-11-24T19:09:24Z"
    val name: String? = null,
    val players: List<TeamPlayer>? = null,
)

data class VideoGame(
    val id: Int,
    val name: String,
)

data class TeamPlayer(
//    val birth_year: String?,
//    val birthday: String?,
    val first_name: String?,
//    val hometown: String?,
    val id: Long?,
//    val image_url: String?,
    val last_name: String?,
    val name: String?,
    val nationality: String?,
    val role: String?,
)