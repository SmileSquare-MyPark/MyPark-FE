package com.smile.mypark.data.remote.dto

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
data class SendCodeRequestDto(
    val phoneNumber: String
)

@Serializable
data class VerifyCodeRequestDto(
    val phoneNumber: String,
    val certificationCode: String
)

@Serializable
data class CommonResponseDto(
    val isSuccess: Boolean = false,
    val code: String? = null,
    val message: String? = null,
    val result: JsonObject? = null
)
