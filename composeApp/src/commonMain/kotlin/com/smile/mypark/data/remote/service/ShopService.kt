package com.smile.mypark.data.remote.service

import com.smile.mypark.data.remote.dto.ApiResult
import com.smile.mypark.data.remote.dto.ShopLikeResultDto
import com.smile.mypark.data.remote.dto.ShopListResultDto

interface ShopService {
    suspend fun getShops(name: String?): ApiResult<ShopListResultDto>
    suspend fun likeShop(shopCode: String): ApiResult<ShopLikeResultDto>
    suspend fun unlikeShop(shopCode: String): ApiResult<ShopLikeResultDto>
}