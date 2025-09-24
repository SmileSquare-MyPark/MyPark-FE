package com.smile.mypark.di

import com.smile.mypark.data.repository.AuthRepositoryImpl
import com.smile.mypark.domain.repository.AuthRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<AuthRepository> { AuthRepositoryImpl(get()) }
}