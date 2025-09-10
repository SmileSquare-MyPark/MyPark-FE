package com.smile.mypark.presentation.my

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
internal fun MyRoute(
    padding: PaddingValues,
) {
    //val viewState by viewModel.viewState.collectAsStateWithLifecycle()

    MyScreen(
        padding = padding,
        //viewState = viewState,
        //onClickDetail = onClickDetail
    )
}

@Composable
private fun MyScreen(
    padding: PaddingValues,
    //viewState: MyContract.MyViewState,
    ///onClickDetail: () -> Unit,
) {

    Column(
        Modifier.padding(padding)
    ) {
        Text("My", fontSize = 30.sp)
    }
}

@Preview
@Composable
private fun PreviewMy() {
    MyScreen(
        padding = PaddingValues(),
//        viewState = MyContract.MyViewState(),
//        onClickDetail = {}
    )
}