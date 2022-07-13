package com.ez.dotarate.di

import com.ez.dotarate.view.gamedetail.GameDetailViewModel
import com.ez.dotarate.view.games.GamesViewModel
import com.ez.dotarate.view.login.LoginViewModel
import com.ez.dotarate.view.main.MainViewModel
import com.ez.dotarate.view.mph.MphViewModel
import com.ez.dotarate.view.profile.ProfileSearchViewModel
import com.ez.dotarate.view.profile.ProfileViewModel
import com.ez.dotarate.view.search.SearchUsersViewModel
import com.ez.dotarate.view.search.SearchViewModel
import com.ez.dotarate.view.splash.SplashViewModel
import com.ez.dotarate.view.steam.SteamViewModel
import com.ez.dotarate.view.team.TeamViewModel
import com.ez.dotarate.view.upcominggames.UpcomingGamesViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val koinViewModelModule = module {

    viewModel { GamesViewModel(get(), get(), get(), get()) }

    viewModel { LoginViewModel(get()) }

    viewModel { MainViewModel(get(), get(), get(), get()) }

    viewModel { MphViewModel(get(), get(), get(), get()) }

    viewModel { ProfileViewModel(get(), get()) }

    viewModel { SplashViewModel(get(), get()) }

    viewModel { SteamViewModel(get(), get()) }

    viewModel { GameDetailViewModel(get(), get()) }

    viewModel { UpcomingGamesViewModel(get(), get()) }

    viewModel { ProfileSearchViewModel(get(), get()) }

    viewModel { SearchViewModel(get(), get()) }

    viewModel { SearchUsersViewModel(get(), get()) }

    viewModel { TeamViewModel(get(), get()) }
}