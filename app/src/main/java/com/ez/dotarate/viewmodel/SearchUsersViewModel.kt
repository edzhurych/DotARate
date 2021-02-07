package com.ez.dotarate.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.ez.domain.model.SearchUser
import com.ez.domain.repository.OpenDotaRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

class SearchUsersViewModel constructor(
    application: Application,
    private val repository: OpenDotaRepository
) : AndroidViewModel(application) {

    val liveSearchUsers: LiveData<PagedList<SearchUser>> by lazy {
        val config = PagedList.Config.Builder()
            .setPageSize(16)
            .setEnablePlaceholders(false)
            .setPrefetchDistance(10) // Количество до конца списка, при достижении которого происходит следующая подгрузка данных
            .build()

        LivePagedListBuilder<Int, SearchUser>(
            repository.getRecentSearchUsers(),
            config
        ).build()
    }

    fun searchUsersByName(name: String) {
        viewModelScope.launch(IO) {
            try {
                val listSearchUsers = repository.searchUsersByName(name)
                if (listSearchUsers.isNotEmpty()) {
                    repository.insertSearchUsers(listSearchUsers)
                    Log.d("MyLogs", "SearchUsersViewModel. РЕЗУЛЬТАТ ЗАПРОСА ПУСТ = $listSearchUsers")
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