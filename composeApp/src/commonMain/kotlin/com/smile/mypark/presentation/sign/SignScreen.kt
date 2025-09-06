package com.smile.mypark.presentation.sign

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
internal fun SignRoute(
    padding: PaddingValues
//    onClickDetail: () -> Unit,
    //viewModel: SignViewModel = hiltViewModel()
) {
    //val viewState by viewModel.viewState.collectAsStateWithLifecycle()

    SignScreen(
        padding = padding
        //viewState = viewState,
        //onClickDetail = onClickDetail
    )
}

@Composable
private fun SignScreen(
    padding: PaddingValues
    //viewState: SignContract.SignViewState,
    ///onClickDetail: () -> Unit,
) {

    Column(
        Modifier.padding(padding)
    ) {
        Text("Sign", fontSize = 30.sp)
    }
}

@Preview
@Composable
private fun PreviewSign() {
    SignScreen(
        padding = PaddingValues(),
//        viewState = SignContract.SignViewState(),
//        onClickDetail = {}
    )
}