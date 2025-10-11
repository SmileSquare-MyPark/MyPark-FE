package com.smile.mypark.presentation.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.smile.mypark.presentation.main.component.MainBottomBar
import com.smile.mypark.presentation.main.component.MainFAB
import com.smile.mypark.presentation.main.component.MainNavHost
import com.smile.mypark.presentation.main.navigation.MainNavigator
import com.smile.mypark.presentation.main.navigation.MainTab
import com.smile.mypark.presentation.main.navigation.rememberMainNavigator
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
internal fun MainScreen(
    navigator: MainNavigator = rememberMainNavigator(),
) {
    Scaffold(
        modifier = Modifier,
        content = { padding ->
            MainNavHost(
                navigator = navigator,
                padding = padding,
            )
        },
        bottomBar = {
            Box(
                contentAlignment = Alignment.Center
            ) {
                MainBottomBar(
                    modifier = Modifier
                        .navigationBarsPadding(),
                    visible = navigator.shouldShowBottomBar(),
                    tabs = MainTab.entries,
                    currentTab = navigator.currentTab,
                    onTabSelected = { navigator.navigate(it) }
                )
                MainFAB(
                    visible = navigator.shouldShowBottomBar(),
                    tab = MainTab.QR_F,
                    modifier = Modifier
                        .offset(y = (-50).dp),
                    onTabSelected = { navigator.navigate(it) }
                )
            }
        },
    )
}

@Preview
@Composable
private fun MainBottomBarPreview() {
    MainScreen()
}