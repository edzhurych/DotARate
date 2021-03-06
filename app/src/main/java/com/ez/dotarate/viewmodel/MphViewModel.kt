package com.ez.dotarate.viewmodel

import android.app.Application
import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.ez.domain.model.Hero
import com.ez.domain.repository.OpenDotaRepository
import com.ez.domain.usecase.GetHeroesDataSourceFactoryUseCase
import com.ez.domain.usecase.GetHeroesUseCase
import com.ez.domain.usecase.SaveHeroesUseCase
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import java.net.SocketTimeoutException
import java.net.UnknownHostException


class MphViewModel(
    application: Application,
    private val getHeroesDataSourceFactory: GetHeroesDataSourceFactoryUseCase,
    private val getHeroesUseCase: GetHeroesUseCase,
    private val saveHeroesUseCase: SaveHeroesUseCase,
) : BaseViewModel(application) {

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
            getHeroesDataSourceFactory(
                isLocal = isLocal,
                scope = viewModelScope,
                id32 = id32
            ), config
        ).build()
    }

    fun getHeroes(id32: Int) {
        viewModelScope.launch(IO) {
            try {
                val listHeroes = getHeroesUseCase(id32)

                Log.d("MyLogs", "MphViewModel. ПОЛУЧЕННЫЕ ДАННЫЕ = ${listHeroes}")
                if (listHeroes.isNotEmpty()) {
                    val inserts = saveHeroesUseCase(listHeroes)
                    Log.d("MyLogs", "MphViewModel. ВСТАВЛЕННЫЕ ЗАПИСИ = ${inserts.size}")
                }
            } catch (e: UnknownHostException) {

            } catch (e: SocketTimeoutException) {

            }
        }
    }
}