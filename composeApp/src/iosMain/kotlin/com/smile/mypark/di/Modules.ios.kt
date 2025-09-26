package com.smile.mypark.di

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.darwin.Darwin
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import org.koin.dsl.module

class IOSNetworkConfig(
    override val baseUrl: String
) : NetworkConfig

actual val platformModule = module {
    single<NetworkConfig> { IOSNetworkConfig(baseUrl = "") }
    single<HttpClientEngine> { Darwin.create() }
}

actual fun provideHttpClient(config: NetworkConfig): HttpClient =
    HttpClient(Darwin) {
        install(Logging) {
            level = LogLevel.ALL
            logger = object : Logger {
                override fun log(message: String) = print("Ktor-iOS: $message")
            }
        }
    }