package com.ez.data.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ez.domain.model.SearchUser

class TopPlayersDataSource(private val listTopPlayers: ArrayList<SearchUser>) :
    PagingSource<Int, SearchUser>() {

    override fun getRefreshKey(state: PagingState<Int, SearchUser>): Int? =
        state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SearchUser> {
        return try {
            val nextPageNumber = params.key ?: 1

            val result = listTopPlayers.subList(
                fromIndex = nextPageNumber,
                toIndex = nextPageNumber + params.loadSize
            )
            LoadResult.Page(result, null, nextPageNumber + params.loadSize)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}