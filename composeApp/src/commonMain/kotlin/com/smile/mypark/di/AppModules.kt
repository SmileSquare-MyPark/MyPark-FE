package com.smile.mypark.di

import com.smile.mypark.presentation.auth.AuthViewModel
import com.smile.mypark.presentation.home.HomeViewModel
import com.smile.mypark.presentation.qr.QrViewModel
import com.smile.mypark.presentation.sign.SignViewModel
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module


val appModule = module {

    viewModelOf(::QrViewModel)
    viewModelOf(::HomeViewModel)
    viewModelOf(::AuthViewModel)
    viewModelOf(::SignViewModel)

}

fun initializeKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(
            sharedNetworkModule,
            platformModule,
            repositoryModule,
            serviceModule,
            useCaseModule,
            appModule
        )
    }
}