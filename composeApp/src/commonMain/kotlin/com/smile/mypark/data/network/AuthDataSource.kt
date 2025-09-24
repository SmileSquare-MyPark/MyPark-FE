package com.smile.mypark.data.network

import com.smile.mypark.data.remote.dto.LoginRequestDto
import com.smile.mypark.data.remote.dto.LoginResponseDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class AuthDataSource(
    private val httpClient: HttpClient
) {
    suspend fun login(uid: String, password: String): LoginResponseDto =
        httpClient.post("api/v1/users/login") {
            contentType(ContentType.Application.Json)
            setBody(LoginRequestDto(uid, password))
        }.body()
}