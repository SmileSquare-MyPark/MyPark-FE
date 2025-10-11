package com.smile.mypark.data.repository

import com.smile.mypark.domain.repository.NaverLoginRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.serialization.Serializable

class NaverLoginRepositoryImpl(
    private val client: HttpClient
) : NaverLoginRepository {
    @Serializable
    private data class NaverMeResponse(
        val resultcode: String,
        val message: String,
        val response: Response?
    ) {
        @Serializable
        data class Response(val id: String? = null)
    }

    override suspend fun fetchUserId(accessToken: String): String {
        val res: NaverMeResponse = client.get("https://openapi.naver.com/v1/nid/me") {
            headers.append("Authorization", "Bearer $accessToken")
        }.body()
        val id = res.response?.id
        require(!id.isNullOrBlank()) { "Failed to fetch Naver id" }
        return id
    }
}