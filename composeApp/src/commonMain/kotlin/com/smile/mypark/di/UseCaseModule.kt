package com.smile.mypark.di

import com.smile.mypark.domain.usecase.LoginUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { LoginUseCase(get()) }
}