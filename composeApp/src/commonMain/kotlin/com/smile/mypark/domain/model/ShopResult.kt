package com.smile.mypark.domain.model

data class ShopResult(
    val shopCode: String,
    val shopName: String,
    val address1: String,
    val address2: String,
    val telephone: String,
    val simulationCnt: Int,
    val shopRating: Double,
    val isLiked: Boolean,
)