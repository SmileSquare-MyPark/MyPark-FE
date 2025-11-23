package com.smile.mypark.data.remote.service

import com.smile.mypark.data.remote.dto.ApiResult
import com.smile.mypark.data.remote.dto.HomeDto

interface HomeService {
    suspend fun getHome(): ApiResult<HomeDto>
}