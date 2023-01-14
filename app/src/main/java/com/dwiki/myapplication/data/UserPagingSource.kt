package com.dwiki.myapplication.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.dwiki.myapplication.network.ApiService
import com.dwiki.myapplication.response.DataItem

class UserPagingSource(private val apiService: ApiService) : PagingSource<Int, DataItem>(){
    override fun getRefreshKey(state: PagingState<Int, DataItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DataItem> {
        return try {
            val position = params.key ?: INITIAL_PAGE_INDEX
            val responseData = apiService.getUsers(position)
            val listUsers = responseData.data

            LoadResult.Page(
                data = listUsers,
                prevKey = if (position == INITIAL_PAGE_INDEX) null else position -1,
                nextKey = if (listUsers.isEmpty()) null else position +1
            )
        }catch (exception: Exception){
            return LoadResult.Error(exception)
        }
    }

    private companion object{
        const val INITIAL_PAGE_INDEX = 1
    }
}