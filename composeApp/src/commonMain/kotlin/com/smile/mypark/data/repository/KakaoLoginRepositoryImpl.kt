package com.smile.mypark.data.repository

import com.smile.mypark.domain.repository.KakaoLoginRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.accept
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import kotlinx.serialization.Serializable

class KakaoLoginRepositoryImpl(
    private val client: HttpClient
) : KakaoLoginRepository {

    @Serializable
    private data class KakaoMeResponse(
        val id: Long? = null
    )

    override suspend fun fetchUserId(accessToken: String): String {
        val res: com.smile.mypark.data.repository.KakaoLoginRepositoryImpl.KakaoMeResponse = client.get("https://kapi.kakao.com/v2/user/me") {
            headers.append(HttpHeaders.Authorization, "Bearer $accessToken")
            accept(ContentType.Application.Json)
        }.body()

        val id = res.id?.toString()?.trim()
        require(!id.isNullOrBlank()) { "Failed to fetch Kakao id" }
        return id
    }
}