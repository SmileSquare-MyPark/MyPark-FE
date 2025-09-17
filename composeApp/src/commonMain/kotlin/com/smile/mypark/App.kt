package com.smile.mypark

import androidx.compose.runtime.*
import com.smile.mypark.presentation.main.MainScreen
import com.smile.mypark.presentation.main.navigation.MainNavigator
import com.smile.mypark.presentation.main.navigation.rememberMainNavigator
import com.smile.mypark.core.ui.theme.MyParkTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MyParkTheme {
        val navigator: MainNavigator = rememberMainNavigator()

        MainScreen(
            navigator = navigator
        )
    }
}