package com.ez.dotarate.di

import com.ez.dotarate.viewmodel.*
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { GamesViewModel(get(), get()) }

    viewModel { LoginViewModel() }

    viewModel { MainViewModel(get(), get(), get()) }

    viewModel { MphViewModel(get(), get()) }

    viewModel { ProfileViewModel(get(), get()) }

    viewModel { SplashViewModel(get(), get()) }

    viewModel { SteamViewModel(get(), get()) }

    viewModel { GameDetailViewModel(get(), get()) }

    viewModel { UpcomingGamesViewModel(get(), get(), get(), get()) }

    viewModel { ProfileSearchViewModel(get(), get()) }

    viewModel { SearchViewModel(get(), get()) }

    viewModel { SearchUsersViewModel(get(), get()) }

    viewModel { TeamViewModel(get()) }
}