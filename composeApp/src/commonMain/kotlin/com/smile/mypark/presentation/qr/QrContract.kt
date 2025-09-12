package com.smile.mypark.presentation.qr

import com.smile.mypark.core.base.ViewEvent
import com.smile.mypark.core.base.ViewSideEffect
import com.smile.mypark.core.base.ViewState


class QrContract {

    data class QrViewState (
        val qrData: String = ""
    ) : ViewState

    sealed class QrSidEffect: ViewSideEffect {}

    sealed class QrEvent: ViewEvent {
        data class ScanQR(val data: String) : QrEvent()
    }
}