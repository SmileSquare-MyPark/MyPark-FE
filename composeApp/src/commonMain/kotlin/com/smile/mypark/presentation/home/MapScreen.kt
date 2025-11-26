package com.smile.mypark.presentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
private fun MapScreen(
    viewModel: MapViewModel = koinViewModel()
) {
    val state by viewModel.viewState.collectAsState()

    LaunchedEffect(Unit) {
        if (!isMapSupported()) {
            openNativeMap()
        }
    }

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is MapContract.SideEffect.Toast -> {
                    // TODO: 플랫폼별 Toast 처리
                }

                MapContract.SideEffect.NavigateBack -> {
                    // TODO: NavController.popBackStack() 등
                }

                MapContract.SideEffect.OpenMenu -> {
                    // TODO: 드로어 / 메뉴 열기
                }

                MapContract.SideEffect.OpenStoreList -> {
                    // TODO: 매장 목록 화면 네비게이션
                }

                is MapContract.SideEffect.MoveCamera -> {
                    // TODO: MyNaverMap actual 구현에서 카메라 이동 처리할 수 있게
                }

                is MapContract.SideEffect.CallTo -> {
                    // TODO: Android에서 ACTION_DIAL Intent 등
                }

                is MapContract.SideEffect.OpenRoute -> {
                    // TODO: 네이버지도 / 지도 앱으로 길찾기 인텐트
                }
            }
        }
    }

    if (!isMapSupported()) {
        Box(Modifier.fillMaxSize()) {
            CircularProgressIndicator(Modifier.align(Alignment.Center))
        }
        return
    }

    Box(Modifier.fillMaxSize()) {

        MyNaverMap(
            modifier = Modifier.fillMaxSize(),
            onReady = {
                viewModel.setEvent(MapContract.Event.MapReady)
            }
        )

        MapOverlayUI(
            keyword = state.keyword,
            onKeywordChange = { viewModel.setEvent(MapContract.Event.KeywordChanged(it)) },
            onSearch = { keyword -> viewModel.setEvent(MapContract.Event.ClickSearch(keyword)) },
            onBack = { viewModel.setEvent(MapContract.Event.ClickBack) },
            onMenu = { viewModel.setEvent(MapContract.Event.ClickMenu) },
            onOpenList = { viewModel.setEvent(MapContract.Event.ClickOpenList) },
            onLocation = { viewModel.setEvent(MapContract.Event.ClickMyLocation) },
            selected = state.selected,
            onCall = { store -> viewModel.setEvent(MapContract.Event.ClickCall(store)) },
            onRoute = { store -> viewModel.setEvent(MapContract.Event.ClickRoute(store)) },
            hasResult = state.stores.isNotEmpty(),
            onAddList = { viewModel.setEvent(MapContract.Event.ClickToggleLike) }
        )

        if (state.isLoading || !state.isMapReady) {
            CircularProgressIndicator(Modifier.align(Alignment.Center))
        }
    }
}

@Preview
@Composable
private fun PreviewMap() {
    MapScreen()
}