package com.ez.dotarate.di

import android.app.Application
import androidx.room.Room
import com.ez.dotarate.database.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    internal fun provideDatabase(application: Application) =
        Room.databaseBuilder(application, AppDatabase::class.java, "database")
            .build()
}