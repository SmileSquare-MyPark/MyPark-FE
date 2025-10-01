package com.smile.mypark

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.ContextWrapper
import android.os.Bundle
import com.navercorp.nid.NaverIdLoginSDK
import com.navercorp.nid.oauth.OAuthLoginCallback
import com.smile.mypark.presentation.auth.NaverLoginGateway
import kotlinx.coroutines.suspendCancellableCoroutine
import java.lang.ref.WeakReference
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

private tailrec fun Context.findActivity(): Activity? = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> null
}

object ActivityHolder : Application.ActivityLifecycleCallbacks {
    @Volatile private var _current: WeakReference<Activity>? = null
    val current: Activity? get() = _current?.get()

    override fun onActivityResumed(a: Activity) {
        _current = WeakReference(a)
    }
    override fun onActivityDestroyed(a: Activity) {
        if (_current?.get() === a) _current = null
    }

    override fun onActivityCreated(a: Activity, b: Bundle?) {}
    override fun onActivityStarted(a: Activity) {}
    override fun onActivityPaused(a: Activity) {}
    override fun onActivityStopped(a: Activity) {}
    override fun onActivitySaveInstanceState(a: Activity, outState: Bundle) {}
}