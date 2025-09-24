package com.smile.mypark.data.mapper

import com.smile.mypark.data.remote.dto.LoginResponseDto
import com.smile.mypark.domain.model.AuthResult

fun LoginResponseDto.toDomain(): AuthResult {
    val result = result ?: error(message?.ifBlank { "응답에 result가 없습니다." } ?: "응답에 result가 없습니다.")
    return AuthResult(
        accessToken = result.accessToken,
        refreshToken = result.refreshToken,
        accessTokenExpiration = result.accessTokenExpiration,
        refreshTokenExpiration = result.refreshTokenExpiration
    )
}