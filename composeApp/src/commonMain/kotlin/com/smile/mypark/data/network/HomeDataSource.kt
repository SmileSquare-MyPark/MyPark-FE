package com.smile.mypark.data.network

import com.smile.mypark.data.remote.dto.ApiResult
import com.smile.mypark.data.remote.dto.HomeDto
import com.smile.mypark.data.remote.service.HomeService
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.contentType

class HomeDataSource(
    private val httpClient: HttpClient
) : HomeService {

    override suspend fun getHome(): ApiResult<HomeDto> =
        httpClient.get("api/v1/home") {
            contentType(ContentType.Application.Json)
        }.body()
}