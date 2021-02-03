package com.ez.dotarate.di

import com.ez.dotarate.view.fragments.LoginFragment
import com.ez.dotarate.view.fragments.SteamFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class StartFragmentModule {
    /*
    * We define the name of the Fragment we are going
    * to inject the ViewModelFactory into.
    */
    @ContributesAndroidInjector
    internal abstract fun contributeLoginFragment(): LoginFragment

    @ContributesAndroidInjector
    internal abstract fun contributeSteamFragment(): SteamFragment
}