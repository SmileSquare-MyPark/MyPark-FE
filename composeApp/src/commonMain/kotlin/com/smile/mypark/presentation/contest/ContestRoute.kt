package com.smile.mypark.presentation.contest

import MyParkTabsWithPager
import MyParkTopBar
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.smile.mypark.core.ui.theme.White
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
internal fun ContestRoute(
    padding: PaddingValues,
    navigateToDetail: (Long) -> Unit,
    //viewModel: ContestViewModel = hiltViewModel()
) {
    //val viewState by viewModel.viewState.collectAsStateWithLifecycle()

    ContestScreen(
        padding = padding,
        onClickDetail = navigateToDetail
        //viewState = viewState,
        //onClickDetail = onClickDetail
    )
}

@Composable
private fun ContestScreen(
    padding: PaddingValues,
    onClickDetail: (Long) -> Unit,
    // viewState: ContestContract.ContestViewState,
    // onClickDetail: () -> Unit,
) {
    val tabs = listOf("대회 리스트", "참여중인 대회")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
            .background(White)
    ) {
        MyParkTopBar(
            onMenuClick = {
                // TODO 메뉴 버튼 클릭 시
            }
        )

        MyParkTabsWithPager(
            tabs = tabs,
            startIndex = 0,
            modifier = Modifier.weight(1f)
        ) { idx ->
            when (idx) {
                0 -> ContestListPage(
                    onClickContest = { item ->
                        onClickDetail(item.id)
                    }
                )
                1 -> ContestJoinedPage()
            }
        }
    }
}

@Preview
@Composable
private fun PreviewContest() {
    ContestScreen(
        padding = PaddingValues(),
        onClickDetail = {},
//        viewState = ContestContract.ContestViewState(),
//        onClickDetail = {}
    )
}