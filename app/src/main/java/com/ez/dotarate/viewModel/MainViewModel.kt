package com.ez.dotarate.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.ez.domain.repository.UserRepository
import java.util.*
import javax.inject.Inject


class MainViewModel @Inject
constructor(
    val mBackStack: Stack<String>,
    var currentNavController: MutableLiveData<NavController>,
    private val repository: UserRepository
) : ViewModel() {

    val userNameLive = repository.getUserName()
}