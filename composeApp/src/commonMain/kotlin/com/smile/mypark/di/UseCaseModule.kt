package com.smile.mypark.di

import com.smile.mypark.domain.usecase.LoginUseCase
import com.smile.mypark.domain.usecase.RegisterUseCase
import com.smile.mypark.domain.usecase.SendVerificationCodeUseCase
import com.smile.mypark.domain.usecase.ShopLikeUseCase
import com.smile.mypark.domain.usecase.ShopSearchUseCase
import com.smile.mypark.domain.usecase.ShopUnlikeUseCase
import com.smile.mypark.domain.usecase.VerifyCodeUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { LoginUseCase(get(),get()) }
    factory { SendVerificationCodeUseCase(get()) }
    factory { VerifyCodeUseCase(get()) }
    factory { RegisterUseCase(get()) }
    factory { ShopSearchUseCase(get()) }
    factory { ShopLikeUseCase(get()) }
    factory { ShopUnlikeUseCase(get()) }
}