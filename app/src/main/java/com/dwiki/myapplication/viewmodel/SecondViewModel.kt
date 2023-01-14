package com.dwiki.myapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SecondViewModel: ViewModel() {
    val name = MutableLiveData<String>()
    val user = MutableLiveData<String>()

    fun setName(name: String){
        this.name.value = name
    }

    fun setUser(user: String){
        this.user.value = user
    }
}