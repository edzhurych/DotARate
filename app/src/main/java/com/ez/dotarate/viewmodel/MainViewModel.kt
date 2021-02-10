package com.ez.dotarate.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import com.ez.domain.repository.UserRepository
import java.util.*


class MainViewModel(
    application: Application,
    val mBackStack: Stack<String>,
    var currentNavController: MutableLiveData<NavController>,
    private val repository: UserRepository
) : BaseViewModel(application) {

    val userNameLive = repository.getUserName()
}