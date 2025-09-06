package com.smile.mypark.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
internal fun HomeRoute(
    padding: PaddingValues,
    //viewModel: HomeViewModel = hiltViewModel()
) {
    //val viewState by viewModel.viewState.collectAsStateWithLifecycle()

    HomeScreen(
        padding = padding,
        //viewState = viewState,
        //onClickDetail = onClickDetail
    )
}

@Composable
private fun HomeScreen(
    padding: PaddingValues,
    //viewState: HomeContract.HomeViewState,
    ///onClickDetail: () -> Unit,
) {

    Column(
        Modifier.padding(padding)
    ) {
        Text("HOME", fontSize = 30.sp)
    }
}

@Preview
@Composable
private fun PreviewHome() {
    HomeScreen(
        padding = PaddingValues(),
//        viewState = HomeContract.HomeViewState(),
//        onClickDetail = {}
    )
}