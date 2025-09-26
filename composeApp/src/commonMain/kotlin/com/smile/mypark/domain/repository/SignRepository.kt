package com.smile.mypark.domain.repository

interface SignRepository {
    suspend fun sendVerificationCode(phoneNumber: String)
    suspend fun verifyCode(phoneNumber: String, certificationCode: String)
}