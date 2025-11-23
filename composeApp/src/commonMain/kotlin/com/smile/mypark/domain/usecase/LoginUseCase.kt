package com.smile.mypark.domain.usecase

import com.smile.mypark.domain.repository.DataStoreRepository
import com.smile.mypark.data.remote.dto.type.AuthType
import com.smile.mypark.domain.model.AuthResult
import com.smile.mypark.domain.repository.AuthRepository

class LoginUseCase(private val repository: AuthRepository, private val localStorage: DataStoreRepository) {
    suspend operator fun invoke(uid: String, password: String, type: AuthType): AuthResult {
        require(uid.isNotBlank()) { "아이디/비밀번호를 확인해주세요." }

        return repository.login(uid.trim(), password, type).also { res ->
            localStorage.setAccessToken(res.accessToken)
            localStorage.setRefreshToken(res.refreshToken)
        }
    }
}