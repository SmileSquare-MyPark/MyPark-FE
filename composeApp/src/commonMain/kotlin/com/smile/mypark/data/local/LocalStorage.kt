package com.smile.mypark.data.local

interface LocalStorage {
    suspend fun setAccessToken(token: String)
    suspend fun getAccessToken(): String?

    suspend fun setRefreshToken(refreshToken: String)
    suspend fun getRefreshToken(): String?

    suspend fun setFirstRun(firstRun: Boolean)
    suspend fun getFirstRun(): Boolean

    suspend fun clear()
}