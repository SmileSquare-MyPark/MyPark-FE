package com.smile.mypark.di

import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module


val appModule = module {

}

fun initializeKoin() {
    startKoin {
        modules(appModule)
    }
}