package com.smile.mypark.data.mapper

import com.smile.mypark.data.remote.dto.LoginResponseDto
import com.smile.mypark.domain.model.AuthResult

fun LoginResponseDto.toDomain(): AuthResult =
    AuthResult(
        accessToken = result!!.accessToken,
        refreshToken = result.refreshToken,
        accessTokenExpiration = result.accessTokenExpiration,
        refreshTokenExpiration = result.refreshTokenExpiration
    )