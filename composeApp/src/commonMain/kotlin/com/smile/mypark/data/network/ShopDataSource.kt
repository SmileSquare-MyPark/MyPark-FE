package com.smile.mypark.data.network

import com.smile.mypark.data.remote.dto.ApiResult
import com.smile.mypark.data.remote.dto.ShopLikeResultDto
import com.smile.mypark.data.remote.dto.ShopListResultDto
import com.smile.mypark.data.remote.service.ShopService
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post

class ShopDataSource(
    private val httpClient: HttpClient
): ShopService {
    override suspend fun getShops(name: String?): ApiResult<ShopListResultDto> =
        httpClient.get("/api/v1/shops") {
            name?.takeIf { it.isNotBlank() }?.let {
                parameter("name", it)
            }
        }.body()

    override suspend fun likeShop(shopCode: String): ApiResult<ShopLikeResultDto> =
        httpClient.post("/api/v1/shops/$shopCode/like")
            .body()

    override suspend fun unlikeShop(shopCode: String): ApiResult<ShopLikeResultDto> =
        httpClient.delete("/api/v1/shops/$shopCode/like")
            .body()
}