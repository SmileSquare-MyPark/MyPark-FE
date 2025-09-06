package com.smile.mypark.presentation.qr

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
internal fun QrRoute(
    padding: PaddingValues,
    //viewModel: QrViewModel = hiltViewModel()
) {
    //val viewState by viewModel.viewState.collectAsStateWithLifecycle()

    QrScreen(
        padding = padding,
        //viewState = viewState,
        //onClickDetail = onClickDetail
    )
}

@Composable
private fun QrScreen(
    padding: PaddingValues,
    //viewState: QrContract.QrViewState,
    ///onClickDetail: () -> Unit,
) {

    Column(
        Modifier.padding(padding)
    ) {
        Text("Qr", fontSize = 30.sp)
    }
}

@Preview
@Composable
private fun PreviewQr() {
    QrScreen(
        padding = PaddingValues(),
//        viewState = QrContract.QrViewState(),
//        onClickDetail = {}
    )
}