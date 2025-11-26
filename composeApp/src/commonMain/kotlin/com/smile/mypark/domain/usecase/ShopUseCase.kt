package com.smile.mypark.domain.usecase

import com.smile.mypark.domain.model.ShopResult
import com.smile.mypark.domain.repository.ShopRepository

class ShopSearchUseCase(
    private val shopRepository: ShopRepository
) {
    suspend operator fun invoke(name: String?): List<ShopResult> {
        return shopRepository.getShops(name)
    }
}

class ShopLikeUseCase(
    private val shopRepository: ShopRepository
) {
    suspend operator fun invoke(shopCode: String) {
        return shopRepository.likeShop(shopCode)
    }
}

class ShopUnlikeUseCase(
    private val shopRepository: ShopRepository
) {
    suspend operator fun invoke(shopCode: String) =
        shopRepository.unlikeShop(shopCode)
}