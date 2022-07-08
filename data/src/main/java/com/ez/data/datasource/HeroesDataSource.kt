package com.ez.data.datasource

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ez.domain.model.Hero
import com.ez.domain.repository.OpenDotaRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class HeroesDataSource(
    private val scope: CoroutineScope,
    private val repository: OpenDotaRepository,
    private val id32: Int
) : PagingSource<Int, Hero>() {

    override fun getRefreshKey(state: PagingState<Int, Hero>): Int? =
        state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Hero> {
        return try {
            val nextPageNumber = params.key ?: 1

    //        scope.launch(IO) {
            val listHeroes = repository.fetchHeroes(id32 = id32)
            LoadResult.Page(
                listHeroes,
                null,
                nextPageNumber + 1
            )
    //        }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}