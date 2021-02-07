package com.ez.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ez.domain.model.Game

/**
 * Аннотацией Entity нам необходимо пометить объект, который мы хотим хранить в базе данных
 * Этот класс будет использован для создания таблицы в базе
 * В качестве имени таблицы будет использовано имя класса
 * А поля таблицы будут созданы в соответствии с полями класса
 */
@Entity(tableName = "games")
data class GameDb internal constructor(
    @field:PrimaryKey
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
) {

    fun toGame() = Game(
        match_id = this.match_id,
        assists = this.assists,
        deaths = this.deaths,
        duration = this.duration,
        game_mode = this.game_mode,
        hero_id = this.hero_id,
        kills = this.kills,
        leaver_status = this.leaver_status,
        lobby_type = this.lobby_type,
        party_size = this.party_size,
        player_slot = this.player_slot,
        radiant_win = this.radiant_win,
        skill = this.skill,
        start_time = this.start_time,
        version = this.version,
    )
}

fun Game.toGameDb() = GameDb(
    match_id = this.match_id,
    assists = this.assists,
    deaths = this.deaths,
    duration = this.duration,
    game_mode = this.game_mode,
    hero_id = this.hero_id,
    kills = this.kills,
    leaver_status = this.leaver_status,
    lobby_type = this.lobby_type,
    party_size = this.party_size,
    player_slot = this.player_slot,
    radiant_win = this.radiant_win,
    skill = this.skill,
    start_time = this.start_time,
    version = this.version,
)

fun List<GameDb>.toGames() = MutableList(size) { index ->
    this[index].toGame()
}

fun List<Game>.toGamesDb() = List(size) { index ->
    this[index].toGameDb()
}