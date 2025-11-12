package com.smile.mypark.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.smile.mypark.core.ext.toFixedSp
import com.smile.mypark.core.ui.component.NotiItem
import com.smile.mypark.core.ui.component.HorizontalLine
import com.smile.mypark.core.ui.component.LogoAppBar
import com.smile.mypark.core.ui.component.RoundShape
import com.smile.mypark.core.ui.component.ScoreItem
import com.smile.mypark.core.ui.component.VerticalLine
import com.smile.mypark.core.ui.theme.Gray20
import com.smile.mypark.core.ui.theme.Gray70
import com.smile.mypark.core.ui.theme.Primary
import com.smile.mypark.core.ui.theme.Sub
import com.smile.mypark.core.ui.theme.White
import mypark.composeapp.generated.resources.Res
import mypark.composeapp.generated.resources.ic_test
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
internal fun HomeRoute(
    padding: PaddingValues,
    onClickOpenMap: () -> Unit,
) {
    val viewModel = koinViewModel<HomeViewModel>()
    val viewState by viewModel.viewState.collectAsStateWithLifecycle()

    HomeScreen(
        padding = padding,
        viewState = viewState,
        onClickOpenMap = onClickOpenMap
    )
}

@Composable
private fun HomeScreen(
    padding: PaddingValues,
    viewState: HomeContract.HomeViewState,
    onClickOpenMap: () -> Unit,

) {

    Column(
        Modifier.fillMaxSize().background(White).padding(padding)
    ) {
        LogoAppBar(reverseColor = true)
        Box(
            Modifier.background(
                Primary,
                shape = RoundedCornerShape(bottomEnd = 16.dp, bottomStart = 16.dp)
            ).padding(12.dp)
        ) {
            RoundShape {
                Column(
                    Modifier.fillMaxWidth().padding(10.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.Bottom
                    ) {
                        Text(
                            viewState.userData.s1,
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontSize = 25.toFixedSp(), lineHeight = 29.toFixedSp()
                            )
                        )
                        Spacer(Modifier.width(2.dp))
                        Text(
                            viewState.userData.s2,
                            style = MaterialTheme.typography.bodySmall.copy(
                                fontSize = 25.toFixedSp(), lineHeight = 29.toFixedSp()
                            )
                        )
                        Spacer(Modifier.width(6.dp))
                        Text(
                            "BEST SHOT!",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontSize = 15.toFixedSp(), lineHeight = 21.toFixedSp()
                            )
                        )

                    }
                    Spacer(Modifier.height(6.dp))
                    Row(
                        modifier = Modifier.height(70.dp)
                    ) {
                        ScoreItem(
                            score = viewState.userData.s1,
                            title = "홀인원",
                            modifier = Modifier.weight(1f)
                        )
                        VerticalLine()
                        ScoreItem(
                            score = viewState.userData.s1,
                            title = "알바트로스",
                            modifier = Modifier.weight(1f)
                        )
                        VerticalLine()
                        ScoreItem(
                            score = viewState.userData.s1,
                            title = "이글",
                            modifier = Modifier.weight(1f)
                        )
                    }
                    Spacer(Modifier.height(6.dp))
                    HorizontalLine()
                    Spacer(Modifier.height(6.dp))
                    Row(
                        modifier = Modifier.height(70.dp)
                    ) {
                        ScoreItem(
                            score = viewState.userData.s1,
                            title = "MY PARK 구력",
                            unit = "개월",
                            modifier = Modifier.weight(1f)
                        )
                        VerticalLine()
                        ScoreItem(
                            score = viewState.userData.s1,
                            title = "최근 5경기 추이",
                            unit = "타",
                            modifier = Modifier.weight(1f)
                        )
                        VerticalLine()
                        ScoreItem(
                            score = viewState.userData.s1,
                            title = "베스트 스코어",
                            unit = "타",
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }
        }
        Column(
            Modifier.padding(16.dp)
        ) {
            RoundShape(
                corner = 10.dp,
                backgroundColor = Sub
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painterResource(Res.drawable.ic_test),
                        null,
                        tint = White,
                    )
                    Spacer(Modifier.width(12.dp))
                    Text(
                        "마이파크 간편 로그인하기",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontSize = 18.toFixedSp(), color = White
                        ),
                        modifier = Modifier.weight(1f)
                    )
                    Icon(
                        painterResource(Res.drawable.ic_test),
                        null,
                        tint = White,
                    )
                }
            }
            Spacer(Modifier.height(16.dp))
            Row (
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(
                    painterResource(Res.drawable.ic_test),
                    null,
                    tint = Primary,
                )
                Text(
                    "공지사항",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 15.toFixedSp(), color = Gray70
                    ),
                )
            }
            RoundShape(
                backgroundColor = Gray20,
            ) {
                Column {
                    viewState.notis.take(4).forEach {
                        NotiItem(
                            title = it.s1,
                            dueDate = it.s2 + "~" + it.s3
                        )
                    }
                }
            }
            Spacer(Modifier.height(16.dp))
            RoundShape(
                corner = 10.dp,
                backgroundColor = Sub
            ) {
                Row(
                    modifier = Modifier.padding(16.dp)
                        .clickable { onClickOpenMap() },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painterResource(Res.drawable.ic_test),
                        null,
                        tint = White,
                    )
                    Spacer(Modifier.width(12.dp))
                    Text(
                        "마이파크 매장 찾기",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontSize = 18.toFixedSp(), color = White
                        ),
                        modifier = Modifier.weight(1f)
                    )
                    Icon(
                        painterResource(Res.drawable.ic_test),
                        null,
                        tint = White,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun PreviewHome() {
    HomeScreen(
        padding = PaddingValues(),
        viewState = HomeContract.HomeViewState(),
        onClickOpenMap = {}
    )
}