package com.smile.mypark.presentation.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import platform.Foundation.NSNotificationCenter

private const val OPEN_NATIVE_MAP = "OPEN_NATIVE_MAP"

actual fun isMapSupported(): Boolean = false

actual fun openNativeMap() {
    NSNotificationCenter.defaultCenter.postNotificationName(
        aName = OPEN_NATIVE_MAP,
        `object` = null,
        userInfo = null
    )
}

@Composable
actual fun MyNaverMap(
    modifier: Modifier,
    onReady: (() -> Unit)?
) { /* no-op */ }