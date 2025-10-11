package com.smile.mypark

import androidx.compose.runtime.Composable

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
expect fun showToast(message: String)
expect fun exitApp()
@Composable
expect fun BackHandlerCompose(onBack: () -> Unit)