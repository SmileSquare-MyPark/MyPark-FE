package com.smile.mypark

import android.app.Application
import com.kakao.sdk.common.KakaoSdk
import com.smile.mypark.di.androidLoginModule
import com.smile.mypark.di.initializeKoin
import org.koin.android.ext.koin.androidContext

class MyParkApp: Application() {

    override fun onCreate() {
        super.onCreate()
        initializeKoin {
            androidContext(this@MyParkApp)
            modules(androidLoginModule)

        }
        KakaoSdk.init(this, getString(R.string.kakao_app_key))

    }
}