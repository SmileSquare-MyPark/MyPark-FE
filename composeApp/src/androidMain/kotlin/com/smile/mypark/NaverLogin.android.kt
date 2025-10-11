package com.smile.mypark

import android.content.Context
import com.navercorp.nid.NaverIdLoginSDK
import com.navercorp.nid.oauth.OAuthLoginCallback
import com.smile.mypark.presentation.auth.NaverLoginGateway
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class NaverLoginGatewayAndroid(
    private val context: Context
) : NaverLoginGateway {

    override suspend fun login(): String = suspendCancellableCoroutine { cont ->
        val activity = context.findActivity()
            ?: ActivityHolder.current
            ?: run {
                cont.resumeWithException(IllegalStateException("No Activity context available"))
                return@suspendCancellableCoroutine
            }

        NaverIdLoginSDK.authenticate(activity, object : OAuthLoginCallback {
            override fun onSuccess() {
                val access = NaverIdLoginSDK.getAccessToken()
                if (!access.isNullOrBlank()) cont.resume(access)
                else cont.resumeWithException(IllegalStateException("No access token"))
            }
            override fun onFailure(httpStatus: Int, message: String) {
                cont.resumeWithException(IllegalStateException("Naver login failed($httpStatus): $message"))
            }
            override fun onError(errorCode: Int, message: String) {
                cont.resumeWithException(IllegalStateException("Naver error($errorCode): $message"))
            }
        })
    }
}