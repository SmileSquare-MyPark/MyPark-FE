package com.smile.mypark.di

import com.smile.mypark.data.local.LocalStorage
import io.ktor.client.HttpClient
import org.koin.core.module.Module
import org.koin.dsl.module

interface NetworkConfig {
    val baseUrl: String
}

expect val platformModule: Module

expect fun provideHttpClient(config: NetworkConfig, localStorage: LocalStorage): HttpClient

//expect fun provideLocalStorage(): LocalStorage
val sharedNetworkModule = module {
    single<HttpClient> { HttpClientFactory.create(get(), get()) }
}