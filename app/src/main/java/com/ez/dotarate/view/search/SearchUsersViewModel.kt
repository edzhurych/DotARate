package com.ez.dotarate.view.search

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.ez.domain.model.SearchUser
import com.ez.domain.repository.OpenDotaRepository
import com.ez.dotarate.view.BaseViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

class SearchUsersViewModel(
    application: Application,
    private val repository: OpenDotaRepository
) : BaseViewModel(application) {

    val liveSearchUsers: LiveData<PagingData<SearchUser>> by lazy {
        Pager(
            PagingConfig(20)
        ) {
            repository.getRecentSearchUsers()
        }.liveData
    }

    fun searchUsersByName(name: String) {
        viewModelScope.launch(IO) {
            try {
                val listSearchUsers = repository.searchUsersByName(name)
                if (listSearchUsers.isNotEmpty()) {
                    repository.insertSearchUsers(listSearchUsers)
                    Log.d(
                        "MyLogs",
                        "SearchUsersViewModel. РЕЗУЛЬТАТ ЗАПРОСА ПУСТ = $listSearchUsers"
                    )
                }
            } catch (e: UnknownHostException) {
                // Нет интернета
            } catch (e: TimeoutException) {
                // Плохое соединение.
            } catch (e: SocketTimeoutException) {
                // Плохое соединение.
            }
        }
    }
}