package com.smile.mypark.data.repository

import com.smile.mypark.data.mapper.toDomain
import com.smile.mypark.data.remote.dto.LoginRequestDto
import com.smile.mypark.data.remote.service.AuthService
import com.smile.mypark.domain.error.NotMemberException
import com.smile.mypark.domain.model.AuthResult
import com.smile.mypark.domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val service: AuthService
) : AuthRepository {
    override suspend fun login(uid: String, password: String): AuthResult {
        val res = service.login(LoginRequestDto(uid, password))

        if (!res.isSuccess || res.result == null) {
            val notMember =
                res.code == "USER4000" || res.message?.contains("사용자를 찾을 수 없습니다") == true

            if (notMember) throw NotMemberException(res.message ?: "사용자를 찾을 수 없습니다.")
            throw IllegalStateException(res.message ?: "로그인 실패")
        }

        return res.toDomain()
    }
}