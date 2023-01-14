package com.dwiki.myapplication.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserPreference private constructor(private val dataStrore: DataStore<Preferences>) {
    suspend fun saveData(selectedUser: String){
        dataStrore.edit {
            it[SELECTED_USER_KEY] = selectedUser
        }
    }

    fun getSelectedUser(): Flow<String> = dataStrore.data.map { preferences ->
        preferences[SELECTED_USER_KEY] ?: "null"
    }

    companion object{
        private val SELECTED_USER_KEY = stringPreferencesKey("user_selected")

        @Volatile
        private var INSTANCE: UserPreference? = null

        fun getInstance(dataStore: DataStore<Preferences>) : UserPreference{
            return INSTANCE ?: synchronized(this){
                val instance = UserPreference(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }
}