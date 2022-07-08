package com.ez.data.datasource

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ez.domain.model.UpcomingGame
import com.ez.domain.repository.PandaScoreRepository

class UpcomingGamesByLeagueDataSource(
    private val repository: PandaScoreRepository,
    private val leagueId: Int,
) : PagingSource<Int, UpcomingGame>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UpcomingGame> {
        try {
            Log.d("MyLogs", "UpcomingGamesByLeagueDataSource. loadInitial")

            val nextPageNumber = params.key ?: 1

            val listUpcomingGames =
                repository.fetchUpcomingMatchesByLeague(
                    leagueId = leagueId,
                    page = nextPageNumber,
                    loadSize = params.loadSize
                )

            Log.d(
                "MyLogs",
                "UpcomingGamesByLeagueDataSource. loadInitial. Response = $listUpcomingGames"
            )

            return LoadResult.Page(listUpcomingGames, null, nextPageNumber + 1)
        } catch (e: Exception) {
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