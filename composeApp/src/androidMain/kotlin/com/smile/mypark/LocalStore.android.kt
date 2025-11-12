package com.smile.mypark

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

class DataStoreManger(
    private val context: Context
) {
    companion object {
        private val Context.dataStore by preferencesDataStore(name = "mypark_ds")

        private val KEY_ON = booleanPreferencesKey(name = "on")
        private val KEY_ACCESS_TOKEN = stringPreferencesKey(name = "access")
        private val KEY_REFRESH_TOKEN = stringPreferencesKey(name = "refresh")
        private val KEY_FIRST_RUN = booleanPreferencesKey(name = "first_run")
    }

    val isOn = context.dataStore.data.map { pref ->
        pref[KEY_ON] ?: true
    }

    suspend fun setAccessToken(token: String) {
        context.dataStore.edit { pref ->
            pref[KEY_ACCESS_TOKEN] = token
        }
    }

    suspend fun getAccessToken(): String? {
        return context.dataStore.data.map { it[KEY_ACCESS_TOKEN] }.firstOrNull()
    }

    suspend fun setRefreshToken(refreshToken: String) {
        context.dataStore.edit { pref ->
            pref[KEY_REFRESH_TOKEN] = refreshToken
        }
    }

    suspend fun getRefreshToken(): String? {
        return context.dataStore.data.map { it[KEY_REFRESH_TOKEN] }.firstOrNull()
    }

    suspend fun setFirstRun(firstRun: Boolean) {
        context.dataStore.edit { pref ->
            pref[KEY_FIRST_RUN] = firstRun
        }
    }

    suspend fun getFirstRun(): Boolean {
        return context.dataStore.data.map { it[KEY_FIRST_RUN] }.firstOrNull() ?: true
    }

    suspend fun clearDataStore() {
        context.dataStore.edit {
            it.clear()
        }
    }
}
