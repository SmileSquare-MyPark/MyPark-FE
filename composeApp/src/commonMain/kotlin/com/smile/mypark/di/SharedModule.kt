package com.smile.mypark.di

import com.smile.mypark.data.network.AuthDataSource
import com.smile.mypark.data.repository.AuthRepositoryImpl
import com.smile.mypark.domain.repository.AuthRepository
import com.smile.mypark.domain.usecase.LoginUseCase
import io.ktor.client.HttpClient
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val sharedModule = module {
    single<HttpClient> { HttpClientFactory.create(get()) }
//
//    singleOf(::AuthDataSource)
//    singleOf(::AuthRepositoryImpl) { bind<AuthRepository>() }
//    factory { LoginUseCase(get()) }
}