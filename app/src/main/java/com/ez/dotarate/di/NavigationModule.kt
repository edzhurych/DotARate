package com.ez.dotarate.di

import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import org.koin.dsl.module
import java.util.*


val koinNavigationModule = module {

    factory { Stack<String>() }

    factory { MutableLiveData<NavController>() }
}