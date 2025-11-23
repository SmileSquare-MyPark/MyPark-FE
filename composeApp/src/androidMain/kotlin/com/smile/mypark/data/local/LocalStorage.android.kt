package com.smile.mypark.data.local

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

class LocalStorageAndroid(
    private val context: Context
) : LocalStorage {

    private val Context.dataStore by preferencesDataStore(name = "mypark_ds")

    companion object {
        private val KEY_ACCESS_TOKEN = stringPreferencesKey("access")
        private val KEY_REFRESH_TOKEN = stringPreferencesKey("refresh")
        private val KEY_FIRST_RUN = booleanPreferencesKey("first_run")
    }

    override suspend fun setAccessToken(token: String) {
        context.dataStore.edit { pref ->
            pref[KEY_ACCESS_TOKEN] = token
        }
    }

    override suspend fun getAccessToken(): String? {
        return context.dataStore.data.first()[KEY_ACCESS_TOKEN]
    }

    override suspend fun setRefreshToken(refreshToken: String) {
        context.dataStore.edit { pref ->
            pref[KEY_REFRESH_TOKEN] = refreshToken
        }
    }

    override suspend fun getRefreshToken(): String? {
        return context.dataStore.data.first()[KEY_REFRESH_TOKEN]
    }

    override suspend fun setFirstRun(firstRun: Boolean) {
        context.dataStore.edit { pref ->
            pref[KEY_FIRST_RUN] = firstRun
        }
    }

    override suspend fun getFirstRun(): Boolean {
        return context.dataStore.data.first()[KEY_FIRST_RUN] ?: true
    }

    override suspend fun clear() {
        context.dataStore.edit { it.clear() }
    }
}