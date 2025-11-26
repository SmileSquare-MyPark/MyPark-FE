package com.smile.mypark.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.ComposeUIViewController
import com.smile.mypark.presentation.home.component.MapLocationButton
import com.smile.mypark.presentation.home.component.MapTopBar
import com.smile.mypark.presentation.home.component.StoreBottomCard
import com.smile.mypark.presentation.home.component.StoreUi
import com.smile.mypark.presentation.home.component.StoreHeartButton
import com.smile.mypark.presentation.home.component.StoreListButton
import platform.UIKit.UIViewController

@Composable
private fun MapOverlayTopBar(
    keyword: TextFieldValue,
    onKeywordChange: (TextFieldValue) -> Unit,
    onBack: () -> Unit,
    onMenu: () -> Unit,
    onSearch: (String) -> Unit,
) {
    Box(Modifier.fillMaxWidth()) {
        MapTopBar(
            keyword = keyword,
            onKeywordChange = onKeywordChange,
            onBack = onBack,
            onMenu = onMenu,
            onSearch = onSearch,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
private fun MapOverlayBottomCard(
    selected: StoreUi?,
    onOpenList: () -> Unit,
    onCall: (StoreUi) -> Unit,
    onRoute: (StoreUi) -> Unit,
) {
    if (selected == null) return

    Box(Modifier
        .fillMaxSize()
        .background(Transparent)
    ) {
        StoreBottomCard(
            store = selected,
            onCall = onCall,
            onRoute = onRoute,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(bottom = 30.dp, start = 15.dp, end = 15.dp)
        )
    }
}

@Composable
private fun MapOverlayFloatButtonsContent(
    isLiked: Boolean,
    onAddList: () -> Unit,
    onOpenList: () -> Unit,
    onLocation: () -> Unit,
) {
    Box(Modifier
        .fillMaxSize()
        .background(Transparent)
    ) {
        StoreHeartButton(
            isLiked = isLiked,
            onAddList = onAddList,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 15.dp, bottom = 0.dp)
        )
        StoreListButton(
            onOpenList = onOpenList,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 0.dp)
        )
        MapLocationButton(
            onLocation = onLocation,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 15.dp, bottom = 0.dp)
        )
    }
}

fun MapOverlayTopBarViewController(
    onBack: () -> Unit,
    onMenu: () -> Unit,
    onSearch: (String) -> Unit,
): UIViewController = ComposeUIViewController {
    var keyword by remember { mutableStateOf(TextFieldValue("")) }
    Box(Modifier.fillMaxWidth().background(Transparent)) {
        MapOverlayTopBar(
            keyword = keyword,
            onKeywordChange = { keyword = it },
            onBack = onBack,
            onMenu = onMenu,
            onSearch = onSearch
        )
    }
}

fun MapOverlayBottomCardViewController(
    selected: StoreUi?,
    onOpenList: () -> Unit,
    onCall: (StoreUi) -> Unit,
    onRoute: (StoreUi) -> Unit,
): UIViewController = ComposeUIViewController {
    MapOverlayBottomCard(
        selected = selected,
        onOpenList = onOpenList,
        onCall = onCall,
        onRoute = onRoute
    )
}

fun MapOverlayFloatButtonsViewController(
    onAddList: () -> Unit,
    onOpenList: () -> Unit,
    onLocation: () -> Unit,
    isLiked: Boolean = false
): UIViewController = ComposeUIViewController {
    MapOverlayFloatButtonsContent(
        isLiked = isLiked,
        onAddList = onAddList,
        onOpenList = onOpenList,
        onLocation = onLocation
    )
}