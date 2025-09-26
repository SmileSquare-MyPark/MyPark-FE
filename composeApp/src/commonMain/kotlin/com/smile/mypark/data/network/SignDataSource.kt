package com.smile.mypark.data.network

import com.smile.mypark.data.remote.dto.CommonResponseDto
import com.smile.mypark.data.remote.dto.RegisterRequestDto
import com.smile.mypark.data.remote.dto.SendCodeRequestDto
import com.smile.mypark.data.remote.dto.VerifyCodeRequestDto
import com.smile.mypark.data.remote.service.SignService
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class SignDataSource(
    private val httpClient: HttpClient
) : SignService {

    override suspend fun sendCode(dto: SendCodeRequestDto): CommonResponseDto =
        httpClient.post("api/v1/sms/send") {
            contentType(ContentType.Application.Json)
            setBody(dto)
        }.body()

    override suspend fun verifyCode(dto: VerifyCodeRequestDto): CommonResponseDto =
        httpClient.post("api/v1/sms/verify") {
            contentType(ContentType.Application.Json)
            setBody(dto)
        }.body()

    override suspend fun register(dto: RegisterRequestDto): CommonResponseDto =
        httpClient.post("api/v1/users/register") {
            contentType(ContentType.Application.Json)
            setBody(dto)
        }.body()
}
