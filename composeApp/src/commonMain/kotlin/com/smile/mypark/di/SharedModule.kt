package com.smile.mypark.di

import com.smile.mypark.domain.repository.DataStoreRepository
import com.smile.mypark.data.repository.DataStoreRepositoryImpl
import io.ktor.client.HttpClient
import org.koin.core.module.Module
import org.koin.dsl.module

interface NetworkConfig {
    val baseUrl: String
}

expect val platformModule: Module
expect val dataStoreModule: Module
expect fun provideHttpClient(config: NetworkConfig, localStorage: DataStoreRepository): HttpClient

//expect fun provideLocalStorage(): LocalStorage
val sharedNetworkModule = module {
    single<HttpClient> { HttpClientFactory.create(get(), get()) }
    single<DataStoreRepository> { DataStoreRepositoryImpl(get()) }
}