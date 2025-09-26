package com.smile.mypark.domain.usecase

import com.smile.mypark.domain.repository.SignRepository

class SendVerificationCodeUseCase(
    private val repository: SignRepository
) {
    suspend operator fun invoke(phoneNumber: String) =
        repository.sendVerificationCode(phoneNumber)
}

class VerifyCodeUseCase(
    private val repository: SignRepository
) {
    suspend operator fun invoke(phoneNumber: String, certificationCode: String) =
        repository.verifyCode(phoneNumber, certificationCode)
}