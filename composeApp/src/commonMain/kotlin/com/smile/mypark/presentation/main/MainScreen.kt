package com.smile.mypark.presentation.main

import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.smile.mypark.presentation.main.component.MainBottomBar
import com.smile.mypark.presentation.main.component.MainNavHost
import com.smile.mypark.presentation.main.navigation.MainNavigator
import com.smile.mypark.presentation.main.navigation.MainTab
import com.smile.mypark.presentation.main.navigation.rememberMainNavigator
import mypark.composeapp.generated.resources.Res
import mypark.composeapp.generated.resources.ic_main_qr
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

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

@Preview
@Composable
private fun MainBottomBarPreview() {
    MainScreen()
}