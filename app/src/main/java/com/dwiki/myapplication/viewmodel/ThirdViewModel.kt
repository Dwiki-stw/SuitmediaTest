package com.dwiki.myapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.dwiki.myapplication.data.UserRepository
import com.dwiki.myapplication.response.DataItem

class ThirdViewModel(private val userRepository: UserRepository): ViewModel() {
    val listUser: LiveData<PagingData<DataItem>> =
        userRepository.getUsers().cachedIn(viewModelScope)
}