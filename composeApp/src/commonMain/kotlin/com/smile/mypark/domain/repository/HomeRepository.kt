package com.smile.mypark.domain.repository

import com.smile.mypark.data.remote.dto.HomeDto

interface HomeRepository {
    suspend fun getHome(): HomeDto
}