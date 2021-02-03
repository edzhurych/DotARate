package com.ez.dotarate.viewModel

import android.app.Application
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ez.dotarate.model.UserResponse
import com.ez.dotarate.model.WinsAndLosses
import com.ez.dotarate.model.repository.UserRepositoryImpl
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

class ProfileSearchViewModel
@Inject constructor(
    application: Application,
    private val userRepository: UserRepositoryImpl
) : AndroidViewModel(application) {

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
                } else {

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