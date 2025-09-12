package com.smile.mypark.presentation.qr

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import org.publicvalue.multiplatform.qrcode.CodeType
import org.publicvalue.multiplatform.qrcode.ScannerWithPermissions

@Composable
internal fun QrRoute(
    padding: PaddingValues,
) {
    val viewModel = koinViewModel<QrViewModel>()
    val viewState by viewModel.viewState.collectAsStateWithLifecycle()

    QrScreen(
        padding = padding,
        viewState = viewState,
        onEvent = viewModel::setEvent
    )
}

@Composable
private fun QrScreen(
    padding: PaddingValues,
    viewState: QrContract.QrViewState,
    onEvent: (QrContract.QrEvent) -> Unit,
) {

    Column(
        Modifier.fillMaxSize().padding(padding)
    ) {
        Text(viewState.qrData)
        ScannerWithPermissions(
            onScanned = { text ->
                onEvent(QrContract.QrEvent.ScanQR(text))
                true
            },
            types = listOf(CodeType.QR)
        )
    }
}

@Preview
@Composable
private fun PreviewQr() {
    QrScreen(
        padding = PaddingValues(),
        viewState = QrContract.QrViewState(),
        onEvent = {}
    )
}