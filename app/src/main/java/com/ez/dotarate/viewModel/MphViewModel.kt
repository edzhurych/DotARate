package com.ez.dotarate.viewModel

import android.app.Application
import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.ez.dotarate.database.Hero
import com.ez.dotarate.model.repository.OpenDotaRepositoryImpl
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject


class MphViewModel
@Inject constructor(
    application: Application,
    private val repository: OpenDotaRepositoryImpl
) : AndroidViewModel(application) {

    var id32: Int = 0
    var isLocal = false

    val isHeroesEmpty = ObservableBoolean(false)
    val isDataReceivedMph = ObservableBoolean(false)

    val liveHeroes: LiveData<PagedList<Hero>> by lazy {
        val config = PagedList.Config.Builder()
            .setPageSize(16)
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(48) // Количество первоначальной загрузки
            .setPrefetchDistance(10) // Количество до конца списка, при достижении которого происходит следующая подгрузка данных
            .build()

        LivePagedListBuilder<Int, Hero>(
            repository.getHeroesDataSourceFactory(
                isLocal = isLocal,
                scope = viewModelScope,
                id32 = id32
            ), config
        ).build()
    }

    fun getHeroes(id32: Int) {
        viewModelScope.launch(IO) {
            try {
                val listHeroes = repository.fetchHeroes(id32)

                Log.d("MyLogs", "MphViewModel. ПОЛУЧЕННЫЕ ДАННЫЕ = ${listHeroes}")
                if (listHeroes.isNotEmpty()) {
                    val inserts = repository.saveHeroes(listHeroes)
                    Log.d("MyLogs", "MphViewModel. ВСТАВЛЕННЫЕ ЗАПИСИ = ${inserts.size}")
                }
            } catch (e: UnknownHostException) {

            } catch (e: SocketTimeoutException) {

            }
        }
    }
}