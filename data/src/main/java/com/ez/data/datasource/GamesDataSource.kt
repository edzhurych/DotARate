package com.ez.data.datasource

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ez.domain.model.Game
import com.ez.domain.repository.OpenDotaRepository
import kotlinx.coroutines.CoroutineScope

class GamesDataSource(
    private val scope: CoroutineScope,
    private val repository: OpenDotaRepository,
    private val id32: Int
) : PagingSource<Int, Game>() {

    override fun getRefreshKey(state: PagingState<Int, Game>): Int? =
        state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Game> {
        return try {
            val nextPageNumber = params.key ?: 1

//            scope.launch(IO) {
            val listGames = repository.fetchMatches(
                id32 = id32,
                loadPosition = nextPageNumber,
                limitSize = params.loadSize
            )
            Log.d("MyLogs", "GamesDataSource. Game Response = $listGames")
            LoadResult.Page(
                data = listGames,
                prevKey = null, // Only paging forward.
                nextKey = nextPageNumber + 1
            )
//            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}