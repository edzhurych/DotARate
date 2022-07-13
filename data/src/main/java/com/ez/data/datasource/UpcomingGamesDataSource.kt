package com.ez.data.datasource

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ez.domain.model.UpcomingGame
import com.ez.domain.repository.PandaScoreRepository

class UpcomingGamesDataSource(
    private val repository: PandaScoreRepository
) : PagingSource<Int, UpcomingGame>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UpcomingGame> {
        try {
            Log.d("mylogs", "UpcomingGamesDataSource. Thread - [${Thread.currentThread().name}]")
            // Start refresh at page 1 if undefined.
            val nextPageNumber = params.key ?: 1
            Log.d("MyLogs", "UpcomingGamesDataSource. load. nextPageNumber - $nextPageNumber")

            val listUpcomingGames =
                repository.fetchUpcomingMatches(
                    page = nextPageNumber,
                    loadSize = params.loadSize
                )

            Log.d(
                "MyLogs",
                "UpcomingGamesDataSource. load. Response = $listUpcomingGames"
            )
            return LoadResult.Page(
                data = listUpcomingGames,
                prevKey = null, // Only paging forward.
                nextKey = nextPageNumber + 1
            )
        } catch (e: Exception) {
            // Handle errors in this block and return LoadResult.Error if it is an
            // expected error (such as a network failure).
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, UpcomingGame>): Int? {
        // Try to find the page key of the closest page to anchorPosition, from
        // either the prevKey or the nextKey, but you need to handle nullability
        // here:
        //  * prevKey == null -> anchorPage is the first page.
        //  * nextKey == null -> anchorPage is the last page.
        //  * both prevKey and nextKey null -> anchorPage is the initial page, so
        //    just return null.
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}