package com.dwiki.myapplication.helper

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dwiki.myapplication.data.UserRepository
import com.dwiki.myapplication.di.Injection
import com.dwiki.myapplication.viewmodel.ThirdViewModel

class ViewModelFactory(private val context: Context): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ThirdViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return ThirdViewModel(Injection.provideRepository(context)) as T
        }
        throw IllegalArgumentException("Unknow ViewModel Class")
    }
}