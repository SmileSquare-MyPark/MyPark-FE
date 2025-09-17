package com.smile.mypark.presentation.home

import androidx.compose.runtime.Immutable
import androidx.compose.ui.input.key.Key.Companion.D
import com.smile.mypark.core.base.ViewEvent
import com.smile.mypark.core.base.ViewSideEffect
import com.smile.mypark.core.base.ViewState
import com.smile.mypark.domain.model.Dummy


class HomeContract {

    @Immutable
    data class HomeViewState (
        val userData: Dummy = Dummy(),
        val notis: List<Dummy> = listOf(
            Dummy(),
            Dummy(),
            Dummy(),
            Dummy(),
            Dummy(),
            Dummy()
        ),
    ) : ViewState

    sealed class HomeSidEffect: ViewSideEffect {}

    sealed class HomeEvent: ViewEvent {

    }
}