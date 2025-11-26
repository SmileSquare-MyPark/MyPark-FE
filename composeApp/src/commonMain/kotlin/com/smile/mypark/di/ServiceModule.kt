package com.smile.mypark.di

import com.smile.mypark.data.network.AuthDataSource
import com.smile.mypark.data.network.HomeDataSource
import com.smile.mypark.data.network.SignDataSource
import com.smile.mypark.data.network.ShopDataSource
import com.smile.mypark.data.remote.service.AuthService
import com.smile.mypark.data.remote.service.HomeService
import com.smile.mypark.data.remote.service.ShopService
import com.smile.mypark.data.remote.service.SignService
import io.ktor.client.HttpClient
import org.koin.dsl.module

val serviceModule = module {
    single<AuthService> { AuthDataSource(get<HttpClient>()) }
    single<SignService> { SignDataSource(get<HttpClient>()) }
    single<HomeService> { HomeDataSource(get<HttpClient>()) }
    single<ShopService> { ShopDataSource(get<HttpClient>()) }
}