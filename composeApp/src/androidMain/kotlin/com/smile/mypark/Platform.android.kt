package com.smile.mypark

import android.content.Context
import android.os.Build
import android.widget.Toast
import org.koin.core.context.GlobalContext

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()
actual fun showToast(message: String) {
    val context = GlobalContext.get().get<Context>()
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}