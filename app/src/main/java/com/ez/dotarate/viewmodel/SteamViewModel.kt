package com.ez.dotarate.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.ez.domain.model.UserId
import com.ez.domain.repository.UserRepository
import kotlinx.coroutines.launch


class SteamViewModel constructor(
    application: Application, private val repository: UserRepository
) : AndroidViewModel(application) {

    fun saveId(id: Long) {
        viewModelScope.launch {
            repository.saveUserId(UserId(id))
        }
    }
}