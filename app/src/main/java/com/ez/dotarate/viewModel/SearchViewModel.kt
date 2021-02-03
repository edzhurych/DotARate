package com.ez.dotarate.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ez.dotarate.database.SearchUser
import com.ez.dotarate.model.repository.OpenDotaRepositoryImpl
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

class SearchViewModel
@Inject constructor(
    application: Application,
    private val repository: OpenDotaRepositoryImpl
) : AndroidViewModel(application) {

    var isExistedFragment = false
    val liveTopPlayers = MutableLiveData<ArrayList<SearchUser>>()

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