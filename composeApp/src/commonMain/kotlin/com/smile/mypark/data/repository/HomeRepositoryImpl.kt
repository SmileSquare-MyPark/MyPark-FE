package com.smile.mypark.data.repository

import com.smile.mypark.data.mapper.toDomain
import com.smile.mypark.data.remote.dto.HomeDto
import com.smile.mypark.data.remote.dto.LoginRequestDto
import com.smile.mypark.data.remote.dto.type.AuthType
import com.smile.mypark.data.remote.service.AuthService
import com.smile.mypark.data.remote.service.HomeService
import com.smile.mypark.domain.error.NotMemberException
import com.smile.mypark.domain.model.AuthResult
import com.smile.mypark.domain.repository.AuthRepository
import com.smile.mypark.domain.repository.HomeRepository

class HomeRepositoryImpl(
    private val service: HomeService
) : HomeRepository {
    override suspend fun getHome(): HomeDto {
        val res = service.getHome()

        return res.result
    }
}