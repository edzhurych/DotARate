package com.ez.dotarate.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.liveData
import com.ez.domain.repository.UserRepository


class SplashViewModel constructor(
    application: Application,
    private val repository: UserRepository
) : AndroidViewModel(application) {

    /**
     * LiveData’s building block already provides a Coroutine Scope where to call
     *  suspend functions like the one in our repository.
     * So let’s use that with the IO Dispatcher since we’re making a network call.
     * The building block will automatically switch to the UI thread to update LiveData value when needed.
     * We don’t even need to make a method to get the User ID Key
     */
    val liveUserId = liveData {
        emit(repository.getUserId())
    }
}