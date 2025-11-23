package com.smile.mypark.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.smile.mypark.domain.repository.DataStoreRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class DataStoreRepositoryImpl(
    private val dataStore: DataStore<Preferences>
) : DataStoreRepository {

    private object Keys {
        val ACCESS = stringPreferencesKey("access")
        val REFRESH = stringPreferencesKey("refresh")
        val FIRST_RUN = booleanPreferencesKey("first_run")
    }

    override suspend fun setAccessToken(token: String) {
        dataStore.edit { it[Keys.ACCESS] = token }
    }

    override suspend fun getAccessToken(): String? =
        dataStore.data.map { it[Keys.ACCESS] }.first()

    override suspend fun setRefreshToken(token: String) {
        dataStore.edit { it[Keys.REFRESH] = token }
    }

    override suspend fun getRefreshToken(): String? =
        dataStore.data.map { it[Keys.REFRESH] }.first()

    override suspend fun setFirstRun(firstRun: Boolean) {
        dataStore.edit { it[Keys.FIRST_RUN] = firstRun }
    }

    override suspend fun getFirstRun(): Boolean =
        dataStore.data.map { it[Keys.FIRST_RUN] ?: true }.first()

    override suspend fun clear() {
        dataStore.edit { it.clear() }
    }
}