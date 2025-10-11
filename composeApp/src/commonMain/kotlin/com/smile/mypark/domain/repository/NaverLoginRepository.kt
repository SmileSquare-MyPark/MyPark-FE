package com.smile.mypark.domain.repository

interface NaverLoginRepository {
    suspend fun fetchUserId(accessToken: String): String
}