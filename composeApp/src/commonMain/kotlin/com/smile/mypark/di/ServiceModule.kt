package com.smile.mypark.di

import com.smile.mypark.data.network.AuthDataSource
import com.smile.mypark.data.remote.service.AuthService
import io.ktor.client.HttpClient
import org.koin.dsl.module

val serviceModule = module {
    single<AuthService> { AuthDataSource(get<HttpClient>()) }
}