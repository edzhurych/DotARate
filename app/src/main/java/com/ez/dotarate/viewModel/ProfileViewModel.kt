package com.ez.dotarate.viewModel

import android.app.Application
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.ez.domain.model.User
import com.ez.dotarate.customClasses.Event
import com.ez.domain.model.UserResponse
import com.ez.domain.model.WinsAndLosses
import com.ez.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException
import javax.inject.Inject


class ProfileViewModel
@Inject constructor(
    application: Application,
    private val repository: UserRepository
) : AndroidViewModel(application) {

    val liveUser = liveData {
        emit(repository.getUser())
    }

    val isDataReceived = ObservableBoolean(false)
    val isNeedPositionToStartGames = ObservableBoolean(false)
    val isNeedPositionToStartMph = ObservableBoolean(false)

    val userResponseLiveData = MutableLiveData<UserResponse>()
    val liveWinsAndLosses = MutableLiveData<WinsAndLosses>()
    val errorLiveData = MutableLiveData<Event<String>>()

    fun getUserResponse(id32: Int) {
        viewModelScope.launch(IO) {
            try {
                val userResponse = repository.getUserResponse(id32)

                if (userResponse != null) {
                    userResponseLiveData.postValue(userResponse)

                } else {
                    errorLiveData.postValue(Event("Проблемы при попытке соединения"))
                }
            } catch (e: UnknownHostException) {
                errorLiveData.postValue(Event("Exception"))
            } catch (e: TimeoutException) {
                errorLiveData.postValue(Event("Плохое соединение. Попробуйте позже"))
            } catch (e: SocketTimeoutException) {
                errorLiveData.postValue(Event("Плохое соединение. Попробуйте позже"))
            }
        }
    }

    fun getWinsAndLosses(id32: Int) {
        viewModelScope.launch(IO) {
            try {
                val winsAndLosses = repository.getWinsAndLosses(id32)

                if (winsAndLosses != null) {
                    liveWinsAndLosses.postValue(winsAndLosses)
                } else {
                    errorLiveData.postValue(Event("Проблемы при попытке соединения"))
                }
            } catch (e: UnknownHostException) {
                errorLiveData.postValue(Event("Exception"))
            } catch (e: SocketTimeoutException) {
                errorLiveData.postValue(Event("Плохое соединение. Попробуйте позже"))
            }
        }
    }

    fun saveUser(user: User) {
        viewModelScope.launch(IO) {
            repository.saveUser(user)
        }
    }

    fun logout() {
        viewModelScope.launch(IO) {
            repository.logout()
        }
    }
}