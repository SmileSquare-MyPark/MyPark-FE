package com.smile.mypark.di

import com.smile.mypark.data.network.AuthDataSource
import io.ktor.client.HttpClient
import org.koin.dsl.module

val serviceModule = module {
    single { AuthDataSource(get<HttpClient>()) }
}