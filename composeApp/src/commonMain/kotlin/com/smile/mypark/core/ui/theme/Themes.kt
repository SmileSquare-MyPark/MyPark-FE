package com.smile.mypark.core.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect

private val ColorScheme = darkColorScheme(
    primary = Primary,
    secondary = Sub,
    tertiary = PrimaryLight
)


@Composable
fun MyParkTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {

    MaterialTheme(
        colorScheme = ColorScheme,
        typography = nanumTypography(),
        content = content
    )
}