package com.smile.mypark.data.network

import com.smile.mypark.data.remote.dto.LoginRequestDto
import com.smile.mypark.data.remote.dto.LoginResponseDto
import com.smile.mypark.data.remote.service.AuthService
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class AuthDataSource(
    private val httpClient: HttpClient
): AuthService {
    override suspend fun login(dto: LoginRequestDto): LoginResponseDto =
        httpClient.post("api/v1/users/login") {
            contentType(ContentType.Application.Json)
            setBody(dto)
        }.body()
}