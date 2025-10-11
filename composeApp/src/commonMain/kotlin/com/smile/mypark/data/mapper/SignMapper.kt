package com.smile.mypark.data.mapper

import com.smile.mypark.data.remote.dto.ApiBaseResponseDto
import com.smile.mypark.data.remote.dto.CommonResponseDto
import com.smile.mypark.data.remote.dto.RegisterRequestDto
import com.smile.mypark.domain.model.RegisterInfo

fun CommonResponseDto.requireSuccessOrThrow(defaultMsg: String): Unit {
    if (!isSuccess) throw IllegalStateException(message?.ifBlank { defaultMsg } ?: defaultMsg)
}


fun RegisterInfo.toDto(): RegisterRequestDto = RegisterRequestDto(
    uid = uid,
    password = password,
    nickname = nickname,
    uidX = uidX,
    kind = kind,
    height = height,
    weight = weight,
    age = age,
    gender = gender,
    isAgreePos = isAgreePos,
    isAgreeAlert = isAgreeAlert
)


fun <T> ApiBaseResponseDto<T>.requireSuccess(): T? {
    if (!isSuccess) error(message ?: "요청 실패")
    return result
}