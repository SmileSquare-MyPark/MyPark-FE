package com.smile.mypark.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.smile.mypark.core.ext.toFixedSp
import com.smile.mypark.core.ui.component.HorizontalLine
import com.smile.mypark.core.ui.component.LogoAppBar
import com.smile.mypark.core.ui.component.RoundShape
import com.smile.mypark.core.ui.component.ScoreItem
import com.smile.mypark.core.ui.component.VerticalLine
import com.smile.mypark.core.ui.theme.Gray20
import com.smile.mypark.core.ui.theme.Primary
import com.smile.mypark.core.ui.theme.Sub
import com.smile.mypark.core.ui.theme.White
import com.smile.mypark.presentation.home.component.MapOverlayUI
import com.smile.mypark.presentation.home.component.StoreUi
import mypark.composeapp.generated.resources.Res
import mypark.composeapp.generated.resources.ic_test
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

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