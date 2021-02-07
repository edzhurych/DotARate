package com.ez.data.di

import android.app.Application
import androidx.room.Room
import com.ez.data.db.AppDatabase
import com.ez.data.db.GameDao
import com.ez.data.db.UserDao
import com.ez.data.db.UserIdDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(context: Application): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "database")
            .build()

    @Provides
    @Singleton
    fun provideUserDao(db: AppDatabase): UserDao = db.userDao()

    @Provides
    @Singleton
    fun provideUserIdDao(db: AppDatabase): UserIdDao = db.userIdDao()

    @Provides
    @Singleton
    fun provideGameDao(db: AppDatabase): GameDao = db.gameDao()

}