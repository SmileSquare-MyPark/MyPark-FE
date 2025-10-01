package com.smile.mypark

import com.smile.mypark.presentation.auth.AuthKakaoResultBridge
import com.smile.mypark.presentation.auth.AuthNaverResultBridge
import com.smile.mypark.presentation.auth.KakaoLoginGateway
import com.smile.mypark.presentation.auth.NaverLoginGateway
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import platform.Foundation.NSNotificationCenter
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException


private const val KAKAO_LOGIN_START: String = "KAKAO_LOGIN_START"
private const val NAVER_LOGIN_START: String = "NAVER_LOGIN_START"


class KakaoLoginGatewayIos : KakaoLoginGateway {
    override suspend fun login(): String = suspendCancellableCoroutine { cont ->
        NSNotificationCenter.defaultCenter.postNotificationName(
            KAKAO_LOGIN_START,
            null
        )
        val job = GlobalScope.launch(Dispatchers.Main) {
            val result = AuthKakaoResultBridge.events.first()
            result.onSuccess {
                if (!cont.isCompleted) cont.resume(it)
            }.onFailure {
                if (!cont.isCompleted) cont.resumeWithException(it)
            }
        }
        cont.invokeOnCancellation { job.cancel() }
    }
}

class NaverLoginGatewayIos : NaverLoginGateway {
    override suspend fun login(): String = suspendCancellableCoroutine { cont ->
        NSNotificationCenter.defaultCenter.postNotificationName(
            NAVER_LOGIN_START,
            null
        )

        val job = GlobalScope.launch(Dispatchers.Main) {
            val result = AuthNaverResultBridge.events.first()
            result.onSuccess { if (!cont.isCompleted) cont.resume(it) }
                .onFailure { if (!cont.isCompleted) cont.resumeWithException(it) }
        }

        cont.invokeOnCancellation { job.cancel() }
    }
}