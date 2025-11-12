package com.smile.mypark.presentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import com.smile.mypark.presentation.home.component.MapOverlayUI
import com.smile.mypark.presentation.home.component.StoreUi
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
expect fun MyNaverMap(
    modifier: Modifier = Modifier,
    onReady: (() -> Unit)? = null
)

expect fun isMapSupported(): Boolean

expect fun openNativeMap()

@Composable
internal fun MapRoute(
    padding: PaddingValues
) {
    MapScreen()
}

@Composable
private fun MapScreen() {
    var ready by remember { mutableStateOf(false) }
    var keyword by remember { mutableStateOf(TextFieldValue("")) }

    val selected by remember {
        mutableStateOf(
            StoreUi(
                name = "마이파크 가산점",
                slots = 14,
                distanceText = "645m",
                address = "금천구 가산동",
                rating = 5f,
                phone = "02-123-4567",
                lat = 37.4789, lng = 126.8811
            )
        )
    }
    LaunchedEffect(Unit) {
        if (!isMapSupported()) openNativeMap()
    }

    if (isMapSupported()) {
        Box(Modifier.fillMaxSize()) {
            MyNaverMap(
                modifier = Modifier.fillMaxSize(),
                onReady = { ready = true }
            )

            MapOverlayUI(
                keyword = keyword,
                onKeywordChange = { keyword = it },
                onSearch = { /* TODO: 검색 로직 */ },
                onBack = { /* TODO */ },
                onMenu = { /* TODO */ },
                onOpenList = { /* TODO: 목록 화면 */ },
                selected = selected,
                onCall = { /* TODO: 전화 */ },
                onRoute = { store ->
                    // TODO: 길찾기(안드로이드: Intent로 Naver Map/지도 앱 열기 등)
                }
            )

            if (!ready) {
                CircularProgressIndicator(Modifier.align(Alignment.Center))
            }
        }
    } else {
        Box(Modifier.fillMaxSize()) {
            CircularProgressIndicator(Modifier.align(Alignment.Center))
        }
    }
}

@Preview
@Composable
private fun PreviewMap() {
    MapScreen()
}