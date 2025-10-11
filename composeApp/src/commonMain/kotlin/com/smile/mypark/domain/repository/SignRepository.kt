package com.smile.mypark.domain.repository

import com.smile.mypark.domain.model.RegisterInfo

interface SignRepository {
    suspend fun sendVerificationCode(phoneNumber: String)
    suspend fun verifyCode(phoneNumber: String, certificationCode: String)
    suspend fun register(info: RegisterInfo)

}