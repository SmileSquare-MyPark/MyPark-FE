package com.smile.mypark.data.remote.service

import com.smile.mypark.data.remote.dto.LoginRequestDto
import com.smile.mypark.data.remote.dto.LoginResponseDto

interface AuthService {
    suspend fun login(dto: LoginRequestDto): LoginResponseDto
}