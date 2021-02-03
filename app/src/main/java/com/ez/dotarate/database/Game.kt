package com.ez.dotarate.database

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Аннотацией Entity нам необходимо пометить объект, который мы хотим хранить в базе данных
 * Этот класс будет использован для создания таблицы в базе
 * В качестве имени таблицы будет использовано имя класса
 * А поля таблицы будут созданы в соответствии с полями класса
 */
@Entity(tableName = "games")
data class Game internal constructor(
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
)