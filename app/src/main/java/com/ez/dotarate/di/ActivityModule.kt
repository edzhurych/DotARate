package com.ez.dotarate.di

import com.ez.dotarate.view.activities.MainActivity
import com.ez.dotarate.view.activities.SplashActivity
import com.ez.dotarate.view.activities.StartActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector(modules = [MainFragmentModule::class])
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun contributeSplashActivity(): SplashActivity

    @ContributesAndroidInjector(modules = [StartFragmentModule::class])
    abstract fun contributeStartActivity(): StartActivity
}