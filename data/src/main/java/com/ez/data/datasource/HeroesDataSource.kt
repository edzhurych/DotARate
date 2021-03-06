package com.ez.data.datasource

import android.util.Log
import androidx.paging.PositionalDataSource
import com.ez.domain.model.Hero
import com.ez.domain.repository.OpenDotaRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class HeroesDataSource(
    private val scope: CoroutineScope,
    private val repository: OpenDotaRepository,
    private val id32: Int
) : PositionalDataSource<Hero>() {

    /**
     * Первоначальная загрузка данных
     */
    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<Hero>) {
        Log.d("MyLogs", "HeroesDataSource. loadInitial")
        scope.launch(IO) {
            val listHeroes = repository.fetchHeroes(id32 = id32)
            if (listHeroes.isNotEmpty()) {
                callback.onResult(listHeroes, 0)
            }
        }
    }

    /**
     * Подгрузка новой порции данных
     */
    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<Hero>) {

    }
}