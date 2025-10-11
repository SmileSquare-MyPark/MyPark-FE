package com.smile.mypark.data.remote.service

import com.smile.mypark.data.remote.dto.CommonResponseDto
import com.smile.mypark.data.remote.dto.RegisterRequestDto
import com.smile.mypark.data.remote.dto.SendCodeRequestDto
import com.smile.mypark.data.remote.dto.VerifyCodeRequestDto

interface SignService {
    suspend fun sendCode(dto: SendCodeRequestDto): CommonResponseDto
    suspend fun verifyCode(dto: VerifyCodeRequestDto): CommonResponseDto
    suspend fun register(dto: RegisterRequestDto): CommonResponseDto

}
