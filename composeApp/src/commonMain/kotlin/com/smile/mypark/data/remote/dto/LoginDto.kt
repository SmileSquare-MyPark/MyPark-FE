package com.smile.mypark.data.remote.dto

import kotlinx.serialization.Serializable


@Serializable
data class LoginRequestDto(
    val uid: String,
    val password: String?
)

@Serializable
data class LoginResponseDto(
    val isSuccess: Boolean = false,
    val code: String? = null,
    val message: String? = null,
    val result: LoginResultDto? = null
)

@Serializable
data class LoginResultDto(
    val accessToken: String,
    val refreshToken: String,
    val accessTokenExpiration: Long,
    val refreshTokenExpiration: Long
)
