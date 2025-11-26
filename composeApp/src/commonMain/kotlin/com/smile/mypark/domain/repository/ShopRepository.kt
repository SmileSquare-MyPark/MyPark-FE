package com.smile.mypark.domain.repository

import com.smile.mypark.domain.model.ShopResult

interface ShopRepository {
    suspend fun getShops(name: String?): List<ShopResult>
    suspend fun likeShop(shopCode: String)
    suspend fun unlikeShop(shopCode: String)
}