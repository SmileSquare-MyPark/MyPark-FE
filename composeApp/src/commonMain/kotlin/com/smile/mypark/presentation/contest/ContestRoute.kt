package com.smile.mypark.presentation.contest

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
internal fun ContestRoute(
    padding: PaddingValues,
    //viewModel: ContestViewModel = hiltViewModel()
) {
    //val viewState by viewModel.viewState.collectAsStateWithLifecycle()

    ContestScreen(
        padding = padding,
        //viewState = viewState,
        //onClickDetail = onClickDetail
    )
}

@Composable
private fun ContestScreen(
    padding: PaddingValues,
    //viewState: ContestContract.ContestViewState,
    ///onClickDetail: () -> Unit,
) {

    Column(
        Modifier.padding(padding)
    ) {
        Text("Contest", fontSize = 30.sp)
    }
}

@Preview
@Composable
private fun PreviewContest() {
    ContestScreen(
        padding = PaddingValues(),
//        viewState = ContestContract.ContestViewState(),
//        onClickDetail = {}
    )
}