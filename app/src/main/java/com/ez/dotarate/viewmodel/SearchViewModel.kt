package com.ez.dotarate.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ez.domain.model.SearchUser
import com.ez.domain.repository.OpenDotaRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class SearchViewModel constructor(
    application: Application,
    private val repository: OpenDotaRepository
) : AndroidViewModel(application) {

    var isExistedFragment = false
    val liveTopPlayers = MutableLiveData<List<SearchUser>>()

    fun getTopPlayers() {
        viewModelScope.launch(IO) {
            try {
                val listSearchUsers = repository.getTopPlayers()

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