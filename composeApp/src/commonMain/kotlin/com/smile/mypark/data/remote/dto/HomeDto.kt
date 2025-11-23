package com.smile.mypark.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class HomeDto(
    val albatrossCount: Int = 0,
    val bestScore: Int = 0,
    val eagleCount: Int = 0,
    val experienceYears: Int = 0,
    val holeInOneCount: Int = 0,
    val noticeContent: String = "",
    val noticeTitle: String = "",
    val recentAvgScore: Double = 0.0,
    val uid: Int = 0,
    val userNickname: String = ""
) {

}