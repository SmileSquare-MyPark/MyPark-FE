package com.smile.mypark.data.repository

import com.smile.mypark.data.mapper.requireSuccessOrThrow
import com.smile.mypark.data.network.SignDataSource
import com.smile.mypark.data.remote.dto.SendCodeRequestDto
import com.smile.mypark.data.remote.dto.VerifyCodeRequestDto
import com.smile.mypark.data.remote.service.SignService
import com.smile.mypark.domain.repository.SignRepository

class SignRepositoryImpl(
    private val service: SignService
) : SignRepository {
    override suspend fun sendVerificationCode(phoneNumber: String) {
        val res = service.sendCode(SendCodeRequestDto(phoneNumber))
        res.requireSuccessOrThrow("인증번호 전송 실패")
    }

    override suspend fun verifyCode(phoneNumber: String, certificationCode: String) {
        val res = service.verifyCode(VerifyCodeRequestDto(phoneNumber, certificationCode))
        res.requireSuccessOrThrow("인증번호 검증 실패")
    }
}
