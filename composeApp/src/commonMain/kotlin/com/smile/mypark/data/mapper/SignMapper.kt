package com.smile.mypark.data.mapper

import com.smile.mypark.data.remote.dto.CommonResponseDto

fun CommonResponseDto.requireSuccessOrThrow(defaultMsg: String): Unit {
    if (!isSuccess) throw IllegalStateException(message?.ifBlank { defaultMsg } ?: defaultMsg)
}