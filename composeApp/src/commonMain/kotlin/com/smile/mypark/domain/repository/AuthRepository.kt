package com.smile.mypark.domain.repository

import com.smile.mypark.domain.model.AuthResult

interface AuthRepository {
    suspend fun login(uid: String, password: String): AuthResult
}