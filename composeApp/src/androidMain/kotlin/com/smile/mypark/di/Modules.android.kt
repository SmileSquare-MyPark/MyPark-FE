package com.smile.mypark.di

import android.app.Activity
import android.content.Context
import android.util.Log
import com.smile.mypark.KakaoLoginGatewayAndroid
import com.smile.mypark.NaverLoginGatewayAndroid
import com.smile.mypark.R
import com.smile.mypark.presentation.auth.KakaoLoginGateway
import com.smile.mypark.presentation.auth.NaverLoginGateway
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import io.ktor.http.takeFrom
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

class AndroidNetworkConfig(private val context: Context) : NetworkConfig {
    override val baseUrl: String
        get() = context.getString(R.string.base_url)
}

actual val platformModule = module {
    single<NetworkConfig> { AndroidNetworkConfig(androidContext()) }
}

actual fun provideHttpClient(config: NetworkConfig): HttpClient =
    HttpClient(OkHttp) {
        install(Logging) {
            level = LogLevel.BODY
            logger =  object : Logger {
                override fun log(message: String) {
                    Log.d("OKHttp-ktor", message)
                }
            }
        }
        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                }
            )
        }
        defaultRequest {
            url { takeFrom(config.baseUrl) }
            header(HttpHeaders.Accept, ContentType.Application.Json)
            contentType(ContentType.Application.Json)
        }
    }

val androidLoginModule = module {
    single<KakaoLoginGateway> { KakaoLoginGatewayAndroid(androidContext()) }
    single<NaverLoginGateway> { NaverLoginGatewayAndroid(androidContext()) }
}