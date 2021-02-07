package com.ez.dotarate.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.ez.domain.model.UserId
import com.ez.domain.repository.UserRepository
import kotlinx.coroutines.launch
import javax.inject.Inject


class SteamViewModel @Inject
constructor(
    application: Application, private val repository: UserRepository
) : AndroidViewModel(application) {

    fun saveId(id: Long) {
        viewModelScope.launch {
            repository.saveUserId(UserId(id))
        }
    }
}