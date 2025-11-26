package com.smile.mypark.presentation.home

import androidx.compose.ui.text.input.TextFieldValue
import com.smile.mypark.core.base.ViewEvent
import com.smile.mypark.core.base.ViewSideEffect
import com.smile.mypark.core.base.ViewState
import com.smile.mypark.presentation.home.component.StoreUi

class MapContract {

    data class State(
        val keyword: TextFieldValue = TextFieldValue(""),
        val isLoading: Boolean = false,
        val stores: List<StoreUi> = emptyList(),
        val selected: StoreUi? = null,
        val error: String? = null,
        val isMapReady: Boolean = false,
    ) : ViewState {
        val hasResult: Boolean get() = stores.isNotEmpty()
    }

    sealed interface Event : ViewEvent {
        // 검색창
        data class KeywordChanged(val value: TextFieldValue) : Event
        data class ClickSearch(val keyword: String) : Event

        // 지도/매장 선택
        data object MapReady : Event
        data class ClickStore(val store: StoreUi) : Event

        // 상단/하단 UI 버튼들
        data object ClickBack : Event
        data object ClickMenu : Event
        data object ClickOpenList : Event
        data object ClickMyLocation : Event
        data class ClickCall(val store: StoreUi) : Event
        data class ClickRoute(val store: StoreUi) : Event
        data object ClickToggleLike : Event
    }

    sealed interface SideEffect : ViewSideEffect {
        data class Toast(val msg: String) : SideEffect

        data object NavigateBack : SideEffect
        data object OpenMenu : SideEffect
        data object OpenStoreList : SideEffect

        // 네이티브 기능들
        data class MoveCamera(val lat: Double, val lng: Double) : SideEffect
        data class CallTo(val phone: String) : SideEffect
        data class OpenRoute(
            val lat: Double,
            val lng: Double,
            val name: String
        ) : SideEffect
    }
}