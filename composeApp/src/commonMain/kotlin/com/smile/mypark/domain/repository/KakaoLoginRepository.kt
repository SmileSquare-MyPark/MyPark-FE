package com.smile.mypark.domain.repository

interface KakaoLoginRepository {
    suspend fun fetchUserId(accessToken: String): String
}