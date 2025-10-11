package com.smile.mypark

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.ContextWrapper
import android.os.Bundle
import java.lang.ref.WeakReference

tailrec fun Context.findActivity(): Activity? = when (this) {
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