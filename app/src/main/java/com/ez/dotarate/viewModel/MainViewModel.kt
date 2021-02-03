package com.ez.dotarate.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.ez.dotarate.model.repository.UserRepositoryImpl
import java.util.*
import javax.inject.Inject


class MainViewModel @Inject
constructor(
    val mBackStack: Stack<String>,
    var currentNavController: MutableLiveData<NavController>,
    private val repository: UserRepositoryImpl
) : ViewModel() {

    val userNameLive = repository.getUserName()
}