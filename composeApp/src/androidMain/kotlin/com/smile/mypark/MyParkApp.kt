package com.smile.mypark

import android.app.Application
import com.smile.mypark.di.initializeKoin
import org.koin.android.ext.koin.androidContext

class MyParkApp: Application() {

    override fun onCreate() {
        super.onCreate()
        initializeKoin {
            androidContext(this@MyParkApp)
        }
    }
}