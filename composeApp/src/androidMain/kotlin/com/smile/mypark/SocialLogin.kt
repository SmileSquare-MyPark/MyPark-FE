package com.smile.mypark

import android.content.Context
import com.kakao.sdk.user.UserApiClient
import com.smile.mypark.presentation.auth.KakaoLoginGateway
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class KakaoLoginGatewayAndroid(
    private val context: Context
) : KakaoLoginGateway {
    override suspend fun login(): String = suspendCancellableCoroutine { cont ->
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
            UserApiClient.instance.loginWithKakaoTalk(context) { token, error ->
                if (error != null) cont.resumeWithException(error)
                else if (token != null) cont.resume(token.accessToken)
                else cont.resumeWithException(IllegalStateException("No token"))
            }
        } else {
            UserApiClient.instance.loginWithKakaoAccount(context) { token, error ->
                if (error != null) cont.resumeWithException(error)
                else if (token != null) cont.resume(token.accessToken)
                else cont.resumeWithException(IllegalStateException("No token"))
            }
        }
    }
}