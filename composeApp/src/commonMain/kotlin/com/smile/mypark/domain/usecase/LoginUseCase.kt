package com.smile.mypark.domain.usecase

import com.smile.mypark.domain.model.AuthResult
import com.smile.mypark.domain.repository.AuthRepository

class LoginUseCase(private val repository: AuthRepository) {
    suspend operator fun invoke(uid: String, password: String): AuthResult {
        require(uid.isNotBlank()) { "아이디/비밀번호를 확인해주세요." }
        return repository.login(uid.trim(), password)
    }
}