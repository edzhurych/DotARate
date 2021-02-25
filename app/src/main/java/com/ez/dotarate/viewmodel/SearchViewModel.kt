package com.ez.dotarate.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ez.domain.model.SearchUser
import com.ez.domain.repository.OpenDotaRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class SearchViewModel(
    application: Application,
    private val repository: OpenDotaRepository
) : BaseViewModel(application) {

    var isExistedFragment = false
    val liveTopPlayers = MutableLiveData<List<SearchUser>>()

    fun getTopPlayers() {
        viewModelScope.launch(IO) {
            try {
                val listSearchUsers = repository.fetchTopPlayers()

                Log.d("MyLogs", "SearchViewModel. РЕЗУЛЬТАТ ЗАПРОСА = $listSearchUsers")
                if (listSearchUsers.isNotEmpty()) {
                    liveTopPlayers.postValue(listSearchUsers)
                }
            } catch (e: UnknownHostException) {

            } catch (e: SocketTimeoutException) {

            }
        }
    }
}