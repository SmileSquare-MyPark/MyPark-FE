package com.smile.mypark.domain.model

data class AuthResult(
    val accessToken: String,
    val refreshToken: String,
    val accessTokenExpiration: Long,
    val refreshTokenExpiration: Long
)
