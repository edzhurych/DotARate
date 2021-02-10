package com.ez.infrastructure.di

import androidx.room.Room
import com.ez.data.db.AppDatabase
import com.ez.data.db.GameDao
import com.ez.data.db.UserDao
import com.ez.data.db.UserIdDao
import org.koin.dsl.module


val koinDatabaseModule = module {

    single<AppDatabase> {
        Room.databaseBuilder(get(), AppDatabase::class.java, "database")
            .build()
    }

    single<UserDao> { get<AppDatabase>().userDao() }

    single<UserIdDao> { get<AppDatabase>().userIdDao() }

    single<GameDao> { get<AppDatabase>().gameDao() }
}