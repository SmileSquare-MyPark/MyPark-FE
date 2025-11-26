package com.smile.mypark.data.repository

import com.smile.mypark.data.mapper.requireSuccessOrThrow
import com.smile.mypark.data.mapper.toDomain
import com.smile.mypark.data.remote.service.ShopService
import com.smile.mypark.domain.model.ShopResult
import com.smile.mypark.domain.repository.ShopRepository

class ShopRepositoryImpl(
    private val service: ShopService
) : ShopRepository {
    override suspend fun getShops(name: String?): List<ShopResult> {
        val res = service.getShops(name)

        if (!res.isSuccess || res.result == null) {
            throw IllegalStateException(res.message ?: "가게 정보 조회 실패")
        }
        val dtoList = res.result.shops
        return dtoList.toDomain()
    }

    override suspend fun likeShop(shopCode: String) {
        val res = service.likeShop(shopCode)

        if (!res.isSuccess) {
            throw IllegalStateException(res.message ?: "매장 찜 실패")
        }
    }

    override suspend fun unlikeShop(shopCode: String) {
        val res = service.unlikeShop(shopCode)

        if (!res.isSuccess) {
            throw IllegalStateException(res.message ?: "매장 찜 취소 실패")
        }
    }
}