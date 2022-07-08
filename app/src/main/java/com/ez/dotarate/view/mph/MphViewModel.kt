package com.ez.dotarate.view.mph

import android.app.Application
import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.ez.domain.model.Hero
import com.ez.domain.usecase.GetHeroesDataSourceFactoryUseCase
import com.ez.domain.usecase.GetHeroesUseCase
import com.ez.domain.usecase.SaveHeroesUseCase
import com.ez.dotarate.view.BaseViewModel
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

    val liveHeroes: LiveData<PagingData<Hero>> by lazy {
        Pager(
            PagingConfig(20)
        ) {
            getHeroesDataSourceFactory(
                isLocal = isLocal,
                scope = viewModelScope,
                id32 = id32
            )
        }.liveData
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