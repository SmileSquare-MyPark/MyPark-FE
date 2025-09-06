package com.smile.mypark.presentation.result

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
internal fun ResultRoute(
    padding: PaddingValues,
    //viewModel: ResultViewModel = hiltViewModel()
) {
    //val viewState by viewModel.viewState.collectAsStateWithLifecycle()

    ResultScreen(
        padding = padding,
        //viewState = viewState,
        //onClickDetail = onClickDetail
    )
}

@Composable
private fun ResultScreen(
    padding: PaddingValues,
    //viewState: ResultContract.ResultViewState,
    ///onClickDetail: () -> Unit,
) {

    Column(
        Modifier.padding(padding)
    ) {
        Text("Result", fontSize = 30.sp)
    }
}

@Preview
@Composable
private fun PreviewResult() {
    ResultScreen(
        padding = PaddingValues(),
//        viewState = ResultContract.ResultViewState(),
//        onClickDetail = {}
    )
}