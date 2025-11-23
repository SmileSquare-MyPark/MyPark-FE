package com.smile.mypark.domain.repository

import com.smile.mypark.data.remote.dto.type.AuthType
import com.smile.mypark.domain.model.AuthResult

interface AuthRepository {
    suspend fun login(uid: String, password: String, type: AuthType): AuthResult
}