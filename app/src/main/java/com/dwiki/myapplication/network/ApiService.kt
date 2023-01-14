package com.dwiki.myapplication.network

import com.dwiki.myapplication.response.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("users")
    suspend fun getUsers(
        @Query("page") page: Int
    ): Response
}