package com.ez.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ez.data.model.*


/**
 * Аннотацией Database помечаем основной класс по работе с базой данных
 * Этот класс должен быть абстрактным и наследовать RoomDatabase
 * В Database классе необходимо описать абстрактные методы для получения Dao объектов
 */
@Database(
    entities = [UserIdDb::class, GameDb::class, HeroDb::class, UserDb::class, SearchUserDb::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userIdDao(): UserIdDao
    abstract fun gameDao(): GameDao
    abstract fun heroDao(): HeroDao
    abstract fun userDao(): UserDao
    abstract fun searchUserDao(): SearchUserDao
}