package com.smile.mypark.presentation.home

import androidx.compose.runtime.Immutable
import androidx.compose.ui.input.key.Key.Companion.D
import com.smile.mypark.core.base.ViewEvent
import com.smile.mypark.core.base.ViewSideEffect
import com.smile.mypark.core.base.ViewState
import com.smile.mypark.data.remote.dto.HomeDto
import com.smile.mypark.domain.model.Dummy
import com.smile.mypark.presentation.auth.AuthContract.AuthSideEffect


class HomeContract {

    @Immutable
    data class HomeViewState (
        val homeInfo : HomeDto = HomeDto(),
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

    sealed interface HomeSidEffect: ViewSideEffect {
        data class Toast(val msg: String) : HomeSidEffect
    }

    sealed interface HomeEvent: ViewEvent {

    }
}