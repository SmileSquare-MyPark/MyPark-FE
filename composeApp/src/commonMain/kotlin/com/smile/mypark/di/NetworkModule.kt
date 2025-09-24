package com.smile.mypark.di

import io.ktor.client.HttpClient
import org.koin.core.module.Module
import org.koin.dsl.module

interface NetworkConfig {
    val baseUrl: String
}

expect val platformModule: Module

expect fun provideHttpClient(config: NetworkConfig): HttpClient

val sharedNetworkModule = module {
    single<HttpClient> { provideHttpClient(get()) }
}