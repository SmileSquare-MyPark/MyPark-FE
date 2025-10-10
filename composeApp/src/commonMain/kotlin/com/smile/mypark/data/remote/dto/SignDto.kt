package com.smile.mypark.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement
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
    val result: JsonElement? = null
)

@Serializable
data class RegisterRequestDto(
    val uid: String? = null,
    val password: String? = null,
    val nickname: String,
    val uidX: String? = null,
    val kind: String? = null,
    val height: Int? = null,
    val weight: Int? = null,
    val age: Int? = null,
    val gender: String? = null,
    val isAgreePos: Boolean? = null,
    val isAgreeAlert: Boolean? = null
)

@Serializable
data class ApiBaseResponseDto<T>(
    @SerialName("isSuccess") val isSuccess: Boolean,
    @SerialName("code") val code: String? = null,
    @SerialName("message") val message: String? = null,
    @SerialName("result") val result: T? = null
)
