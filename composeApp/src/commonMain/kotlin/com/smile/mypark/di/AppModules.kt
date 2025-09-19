package com.smile.mypark.di

import com.smile.mypark.presentation.home.HomeViewModel
import com.smile.mypark.presentation.qr.QrViewModel
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module


val appModule = module {

    viewModelOf(::QrViewModel)
    viewModelOf(::HomeViewModel)
}

fun initializeKoin() {
    startKoin {
        modules(appModule)
    }
}