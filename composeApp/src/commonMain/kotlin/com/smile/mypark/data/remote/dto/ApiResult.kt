package com.smile.mypark.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiResult<T>(
    @SerialName("isSuccess") val isSuccess: Boolean,
    @SerialName("code") val code: String? = null,
    @SerialName("message") val message: String? = null,
    @SerialName("result") val result: T
)