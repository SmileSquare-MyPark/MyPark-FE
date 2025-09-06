package com.smile.mypark.presentation.main

import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.smile.mypark.presentation.main.component.MainBottomBar
import com.smile.mypark.presentation.main.component.MainNavHost
import com.smile.mypark.presentation.main.navigation.MainNavigator
import com.smile.mypark.presentation.main.navigation.MainTab
import com.smile.mypark.presentation.main.navigation.rememberMainNavigator

@Composable
internal fun MainScreen(
    navigator: MainNavigator = rememberMainNavigator(),
){
    Scaffold(
        modifier = Modifier,
        content = { padding ->
            MainNavHost(
                navigator = navigator,
                padding = padding,
            )
        },
        bottomBar = {
            MainBottomBar(
                modifier = Modifier
                    .navigationBarsPadding(),
                visible = navigator.shouldShowBottomBar(),
                tabs = MainTab.entries,
                currentTab = navigator.currentTab,
                onTabSelected = { navigator.navigate(it) }
            )
        },
    )
}