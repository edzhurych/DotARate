package com.ez.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ez.domain.model.Game
import com.ez.domain.model.Hero

/**
 * Аннотацией Entity нам необходимо пометить объект, который мы хотим хранить в базе данных
 * Этот класс будет использован для создания таблицы в базе
 * В качестве имени таблицы будет использовано имя класса
 * А поля таблицы будут созданы в соответствии с полями класса
 */

@Entity(tableName = "heroes")
data class HeroDb(
    @field:PrimaryKey
    val hero_id: Int,
    val games: Int,
    val last_played: Int,
    val win: Int
) {

    fun toHero() = Hero(
        hero_id = this.hero_id,
        games = this.games,
        last_played = this.last_played,
        win = this.win,
    )
}

fun Hero.toHeroDb() = HeroDb(
    hero_id = this.hero_id,
    games = this.games,
    last_played = this.last_played,
    win = this.win,
)

fun List<HeroDb>.toHeroes() = MutableList(size) { index ->
    this[index].toHero()
}

fun List<Hero>.toHeroesDb() = List(size) { index ->
    this[index].toHeroDb()
}