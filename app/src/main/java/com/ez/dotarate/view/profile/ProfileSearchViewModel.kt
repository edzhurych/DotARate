package com.ez.dotarate.view.profile

import android.app.Application
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ez.domain.model.UserResponse
import com.ez.domain.model.WinsAndLosses
import com.ez.domain.repository.UserRepository
import com.ez.dotarate.view.BaseViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class ProfileSearchViewModel(
    application: Application,
    private val userRepository: UserRepository
) : BaseViewModel(application) {

    val liveUser = MutableLiveData<UserResponse>()
    val liveWinsAndLosses = MutableLiveData<WinsAndLosses>()

    val isDataReceived = ObservableBoolean(false)
    val isNeedPositionToStartGames = ObservableBoolean(false)
    val isNeedPositionToStartMph = ObservableBoolean(false)

    fun fetchUser(id32: Int) {
        viewModelScope.launch(IO) {
            try {
                val userResponse = userRepository.getUserResponse(id32)
                if (userResponse != null) {
                    liveUser.postValue(userResponse)
                }
            } catch (e: UnknownHostException) {

            } catch (e: SocketTimeoutException) {

            }
        }
    }

    fun fetchWinsAndLosses(id32: Int) {
        viewModelScope.launch(IO) {
            try {
                val winsAndLosses = userRepository.getWinsAndLosses(id32)
                if (winsAndLosses != null) {
                    liveWinsAndLosses.postValue(winsAndLosses)
                } else {

                }
            } catch (e: UnknownHostException) {

            } catch (e: SocketTimeoutException) {

            }
        }
    }
}