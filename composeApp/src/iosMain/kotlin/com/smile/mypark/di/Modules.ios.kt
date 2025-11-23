package com.smile.mypark.di

import com.smile.mypark.Config
import com.smile.mypark.KakaoLoginGatewayIos
import com.smile.mypark.NaverLoginGatewayIos
import com.smile.mypark.domain.repository.DataStoreRepository
import com.smile.mypark.data.local.createDataStore
import com.smile.mypark.presentation.auth.KakaoLoginGateway
import com.smile.mypark.presentation.auth.NaverLoginGateway
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.darwin.Darwin
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.takeFrom
import kotlinx.coroutines.runBlocking
import org.koin.core.module.Module
import org.koin.dsl.module

class IOSNetworkConfig(
    override val baseUrl: String
) : NetworkConfig

actual val platformModule = module {
    single<NetworkConfig> { IOSNetworkConfig(baseUrl = Config.BASE_URL) }
    single<HttpClientEngine> { Darwin.create() }
    single<KakaoLoginGateway> { KakaoLoginGatewayIos() }
    single<NaverLoginGateway> { NaverLoginGatewayIos() }
}

actual val dataStoreModule: Module
    get() = module { single { createDataStore() } }

actual fun provideHttpClient(config: NetworkConfig, localStorage: DataStoreRepository): HttpClient =
    HttpClient(Darwin) {
        install(Logging) {
            level = LogLevel.ALL
            logger = object : Logger {
                override fun log(message: String) = print("Ktor-iOS: $message")
            }
        }

        defaultRequest {
            url { takeFrom(config.baseUrl) }

            // 요청마다 토큰 읽어서 Authorization 헤더 자동 추가
            val token = runBlocking { localStorage.getAccessToken() }
            if (token != null) {
                header("Authorization", "Bearer $token")
            }

            header(HttpHeaders.ContentType, ContentType.Application.Json)
        }
    }