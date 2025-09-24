package com.smile.mypark.data.repository

import com.smile.mypark.data.mapper.toDomain
import com.smile.mypark.data.network.AuthDataSource
import com.smile.mypark.data.remote.dto.LoginRequestDto
import com.smile.mypark.domain.model.AuthResult
import com.smile.mypark.domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val authDataSource: AuthDataSource
) : AuthRepository {
    override suspend fun login(uid: String, password: String): AuthResult {
        val res = authDataSource.login(uid, password)
        if (!res.isSuccess) throw IllegalStateException(res.message)
        return res.toDomain()
    }
}