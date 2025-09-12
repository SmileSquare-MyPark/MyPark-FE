package com.smile.mypark.presentation.qr

import com.smile.mypark.core.base.BaseViewModel

class QrViewModel (

) : BaseViewModel<QrContract.QrViewState, QrContract.QrSidEffect, QrContract.QrEvent>(
    QrContract.QrViewState()
) {
    override fun handleEvents(event:QrContract.QrEvent) {
        when(event){
            is QrContract.QrEvent.ScanQR -> updateState { copy(qrData = event.data) }
        }
    }
}