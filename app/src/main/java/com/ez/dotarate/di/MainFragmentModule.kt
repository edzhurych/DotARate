package com.ez.dotarate.di

import com.ez.dotarate.view.fragments.*
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentModule {

    /*
    * We define the name of the Fragment we are going
    * to inject the ViewModelFactory into.
    */
    @ContributesAndroidInjector
    internal abstract fun contributeGamesFragment(): GamesFragment

    @ContributesAndroidInjector
    internal abstract fun contributeMphFragment(): MphFragment

    @ContributesAndroidInjector
    internal abstract fun contributeProfileFragment(): ProfileFragment

    @ContributesAndroidInjector
    internal abstract fun contributeGamesDetailFragment(): GameDetailFragment

    @ContributesAndroidInjector
    internal abstract fun contributeFirstFragment(): UpcomingGamesFragment

    @ContributesAndroidInjector
    internal abstract fun contributeProfileSearchFragment(): ProfileSearchFragment

    @ContributesAndroidInjector
    internal abstract fun contributeGamesSearchFragment(): GamesSearchFragment

    @ContributesAndroidInjector
    internal abstract fun contributeMphSearchFragment(): MphSearchFragment

    @ContributesAndroidInjector
    internal abstract fun contributeSearchFragment(): SearchFragment

    @ContributesAndroidInjector
    internal abstract fun contributeSearchUsersFragment(): SearchUsersFragment

    @ContributesAndroidInjector
    internal abstract fun contributeSTeamFragment(): TeamFragment
}