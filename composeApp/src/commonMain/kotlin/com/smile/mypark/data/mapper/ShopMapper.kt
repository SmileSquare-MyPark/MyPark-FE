package com.smile.mypark.data.mapper

import com.smile.mypark.data.remote.dto.ShopDto
import com.smile.mypark.domain.model.ShopResult
import com.smile.mypark.presentation.home.component.StoreUi

fun ShopDto.toDomain(): ShopResult =
    ShopResult(
        shopCode = shopCode,
        shopName = shopName,
        address1 = address1,
        address2 = address2,
        telephone = telephone,
        simulationCnt = simulationCnt,
        shopRating = shopRating,
        isLiked = isLiked,
    )

fun List<ShopDto>.toDomain(): List<ShopResult> = map { it.toDomain() }

fun ShopResult.toUi(): StoreUi =
    StoreUi(
        shopCode = shopCode,
        name = shopName,
        slots = simulationCnt,
        distanceText = "",
        address = "$address1 $address2",
        rating = shopRating.toFloat(),
        phone = telephone,
        lat = 0.0,
        lng = 0.0,
        isLiked = isLiked
    )
