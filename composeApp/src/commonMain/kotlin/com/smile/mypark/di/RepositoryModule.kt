package com.smile.mypark.di

import com.smile.mypark.data.repository.AuthRepositoryImpl
import com.smile.mypark.data.repository.HomeRepositoryImpl
import com.smile.mypark.data.repository.KakaoLoginRepositoryImpl
import com.smile.mypark.data.repository.NaverLoginRepositoryImpl
import com.smile.mypark.data.repository.SignRepositoryImpl
import com.smile.mypark.domain.repository.AuthRepository
import com.smile.mypark.domain.repository.HomeRepository
import com.smile.mypark.domain.repository.KakaoLoginRepository
import com.smile.mypark.domain.repository.NaverLoginRepository
import com.smile.mypark.domain.repository.SignRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<AuthRepository> { AuthRepositoryImpl(get()) }
    single<SignRepository> { SignRepositoryImpl(get()) }
    single<NaverLoginRepository> { NaverLoginRepositoryImpl(get()) }
    single<KakaoLoginRepository> { KakaoLoginRepositoryImpl(get()) }
    single<HomeRepository> { HomeRepositoryImpl(get()) }
}