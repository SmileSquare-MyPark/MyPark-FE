package com.smile.mypark.data.repository

import com.smile.mypark.data.mapper.toDomain
import com.smile.mypark.data.remote.dto.LoginRequestDto
import com.smile.mypark.data.remote.service.AuthService
import com.smile.mypark.domain.model.AuthResult
import com.smile.mypark.domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val service: AuthService
) : AuthRepository {
    override suspend fun login(uid: String, password: String): AuthResult {
        val res = service.login(LoginRequestDto(uid, password))

        if (!res.isSuccess || res.result == null) {
            throw IllegalStateException(res.message ?: "로그인 실패")
        }

        return res.toDomain()
    }
}