package com.smile.mypark

import android.app.Application
import com.kakao.sdk.common.KakaoSdk
import com.navercorp.nid.NaverIdLoginSDK
import com.smile.mypark.di.androidLoginModule
import com.smile.mypark.di.initializeKoin
import org.koin.android.ext.koin.androidContext

class MyParkApp: Application() {

    override fun onCreate() {
        super.onCreate()
        initializeKoin {
            androidContext(this@MyParkApp)
//            modules(androidLoginModule)

        }
        registerActivityLifecycleCallbacks(ActivityHolder)

        KakaoSdk.init(this, getString(R.string.kakao_app_key))
        NaverIdLoginSDK.initialize(this, getString(R.string.naver_client_id),getString(R.string.naver_client_secret),getString(R.string.app_name))

    }
}