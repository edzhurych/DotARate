package com.ez.dotarate.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ez.dotarate.viewModel.*
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelBinder {

    /*
     * This method basically says
     * inject this object into a Map using the @IntoMap annotation
     * */
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(GamesViewModel::class)
    protected abstract fun gamesViewModel(viewModel: GamesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    protected abstract fun loginViewModel(viewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    protected abstract fun mainViewModel(viewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MphViewModel::class)
    protected abstract fun mphViewModel(viewModel: MphViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    protected abstract fun profileViewModel(viewModel: ProfileViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    protected abstract fun splashViewModel(viewModel: SplashViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SteamViewModel::class)
    protected abstract fun steamViewModel(viewModel: SteamViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(GameDetailViewModel::class)
    protected abstract fun gameDetailViewModel(viewModel: GameDetailViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(UpcomingGamesViewModel::class)
    protected abstract fun upcomingGamesViewModel(viewModel: UpcomingGamesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProfileSearchViewModel::class)
    protected abstract fun profileSearchViewModel(viewModel: ProfileSearchViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    protected abstract fun searchViewModel(viewModel: SearchViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SearchUsersViewModel::class)
    protected abstract fun searchUsersViewModel(viewModel: SearchUsersViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TeamViewModel::class)
    protected abstract fun teamViewModel(viewModel: TeamViewModel): ViewModel
}