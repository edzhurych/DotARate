package com.ez.dotarate.viewmodel

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.ez.domain.model.UserId
import com.ez.domain.repository.UserRepository
import kotlinx.coroutines.launch


class SteamViewModel(
    application: Application, private val repository: UserRepository
) : BaseViewModel(application) {

    fun saveId(id: Long) {
        viewModelScope.launch {
            repository.saveUserId(UserId(id))
        }
    }
}