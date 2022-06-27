package com.ez.dotarate.view.steam

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.ez.domain.model.UserId
import com.ez.domain.repository.UserRepository
import com.ez.dotarate.view.BaseViewModel
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