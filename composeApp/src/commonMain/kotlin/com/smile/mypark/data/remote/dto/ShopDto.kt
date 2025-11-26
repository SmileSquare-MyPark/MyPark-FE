package com.smile.mypark.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class ShopDto(
    val shopCode: String,
    val shopName: String,
    val address1: String,
    val address2: String,
    val telephone: String,
    val simulationCnt: Int,
    val shopRating: Double,
    val isLiked: Boolean,
)

@Serializable
data class ShopListResultDto(
    val shops: List<ShopDto>
)

@Serializable
object ShopLikeResultDto