package com.smile.mypark

import android.content.Context
import android.os.Build
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import org.koin.core.context.GlobalContext

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()
actual fun showToast(message: String) {
    val context = GlobalContext.get().get<Context>()
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

private var lastBackPressedTime = 0L
actual fun exitApp() {
    val activity = ActivityHolder.current ?: return
    val currentTime = System.currentTimeMillis()

    if (currentTime - lastBackPressedTime < 2000) {
        activity.finish()
    } else {
        showToast("한 번 더 누르면 종료됩니다")
        lastBackPressedTime = currentTime
    }
}
@Composable
actual fun BackHandlerCompose(onBack: () -> Unit) {
    BackHandler { onBack() }
}