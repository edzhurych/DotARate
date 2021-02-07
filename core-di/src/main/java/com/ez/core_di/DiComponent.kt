package com.ez.core_di

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.ez.data.di.ApiModule
import com.ez.data.di.DatabaseModule
import com.ez.data.di.UpcomingGamesModule
import com.ez.domain.model.UpcomingGame
import com.ez.domain.repository.OpenDotaRepository
import com.ez.domain.repository.PandaScoreRepository
import com.ez.domain.repository.UserRepository
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(modules = [
    DatabaseModule::class,
    ApiModule::class,
    UpcomingGamesModule::class,
])
@Singleton
interface DiComponent {

    fun getOpenDotaRepository(): OpenDotaRepository

    fun getUserRepository(): UserRepository
    fun getPandaScoreRepository(): PandaScoreRepository
    fun getPagedList(): LiveData<PagedList<UpcomingGame>>

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Application): DiComponent
    }
}