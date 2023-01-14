package com.dwiki.myapplication.data

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.dwiki.myapplication.network.ApiService
import com.dwiki.myapplication.response.DataItem

class UserRepository(private val apiService: ApiService) {
    fun getUsers(): LiveData<PagingData<DataItem>>{
        return Pager (
            config = PagingConfig(
                pageSize = 2
            ),
            pagingSourceFactory = {
                UserPagingSource(apiService)
            }
        ).liveData
    }
}