package com.dwiki.myapplication.di

import android.content.Context
import com.dwiki.myapplication.data.UserRepository
import com.dwiki.myapplication.network.ApiConfig

object Injection {
    fun provideRepository(context: Context): UserRepository{
        val apiService = ApiConfig.getApiService()
        return UserRepository(apiService)
    }
}