package com.smile.mypark.presentation.contest

import MyParkTopBar
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.smile.mypark.core.ext.toFixedSp
import com.smile.mypark.core.ui.theme.Black
import com.smile.mypark.core.ui.theme.Gray
import com.smile.mypark.core.ui.theme.Primary
import com.smile.mypark.core.ui.theme.White
import mypark.composeapp.generated.resources.Res
import mypark.composeapp.generated.resources.compose_multiplatform
import mypark.composeapp.generated.resources.ic_trophy
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
internal fun ContestDetailRoute(
    padding: PaddingValues,
    contestId: Long,
    modifier: Modifier = Modifier,
    // viewModel: ContestDetailViewModel = koinViewModel()  // 나중에 붙일 거면 이렇게
) {
    // TODO: contestId 로 상세 데이터 로딩
    // 지금은 더미 데이터
    val dummy = remember(contestId) {
        ContestDetailUi(
            id = contestId,
            title = "제3회 전국 스크린 파크골프 대회",
            rankText = "15위 / 317",
            date = "일시 : 2025.00.00 ~ 2025.00.00",
            place = "장소 : 미리 C.C",
            participants = "참가인원 : 120명 (30팀)",
            prize = "총상금 : 1000 만원"
        )
    }

    ContestDetailScreen(
        padding = padding,
        ui = dummy,
        onMenuClick = { /* TODO: 메뉴 열기 */ },
        modifier = modifier
    )
}

@Composable
fun ContestDetailScreen(
    padding: PaddingValues,
    ui: ContestDetailUi,
    onMenuClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val headerHeight = 45.dp
    val overlap = 10.dp

    Column(
        modifier
            .fillMaxSize()
            .background(White)
            .padding(padding)
    ) {
        MyParkTopBar(onMenuClick = onMenuClick)

        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(
                    top = headerHeight - overlap,
                    bottom = 24.dp
                )
            ) {
                item {
                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .offset(y = (-overlap))
                    ) {
                        Image(
                            painter = painterResource(Res.drawable.compose_multiplatform),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Gray),

                        )
                    }
                }

                item {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                    ) {
                        Icon(
                            painter = painterResource(Res.drawable.ic_trophy),
                            contentDescription = null,
                            tint = Primary,
                            modifier = Modifier.size(21.dp)
                        )
                        Spacer(Modifier.width(6.dp))
                        Text(
                            text = ui.title,
                            style = MaterialTheme.typography.headlineLarge.copy(
                                fontSize = 18.toFixedSp(),
                                color = Primary
                            )
                        )
                    }
                }

                item {
                    Text(
                        modifier = Modifier
                            .padding(horizontal = 16.dp),
                        text = ui.rankText,
                        style = MaterialTheme.typography.headlineLarge.copy(
                            fontSize = 24.toFixedSp(),
                            color = Black
                        )
                    )
                }

                item {
                    Surface(
                        modifier = Modifier
                            .fillMaxWidth(),
                        color = White
                    ) {
                        Column(
                            modifier = Modifier.padding(
                                horizontal = 16.dp,
                                vertical = 12.dp
                            ),
                            verticalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Text(
                                text = ui.date,
                                style = MaterialTheme.typography.bodyLarge.copy(
                                    fontSize = 15.toFixedSp(),
                                    color = Black
                                )
                            )
                            Text(
                                text = ui.place,
                                style = MaterialTheme.typography.bodyLarge.copy(
                                    fontSize = 15.toFixedSp(),
                                    color = Black
                                )
                            )
                            Text(
                                text = ui.participants,
                                style = MaterialTheme.typography.bodyLarge.copy(
                                    fontSize = 15.toFixedSp(),
                                    color = Black
                                )
                            )
                            Text(
                                text = ui.prize,
                                style = MaterialTheme.typography.bodyLarge.copy(
                                    fontSize = 15.toFixedSp(),
                                    color = Black
                                )
                            )
                        }
                    }
                }
            }
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(headerHeight)
                    .align(Alignment.TopCenter),
                shape = RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp),
                color = White
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "대회 목록",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontSize = 25.toFixedSp(),
                            color = Black
                        ),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

data class ContestDetailUi(
    val id: Long,
    val title: String,
    val rankText: String,
    val date: String,
    val place: String,
    val participants: String,
    val prize: String
)

@Preview
@Composable
private fun ContestDetailScreenPreview() {
    ContestDetailScreen(
        padding = PaddingValues(0.dp),
        ui = ContestDetailUi(
            id = 1L,
            title = "제3회 전국 스크린 파크골프 대회",
            rankText = "15위 / 317",
            date = "일시 : 2025.00.00 ~ 2025.00.00",
            place = "장소 : 미리 C.C",
            participants = "참가인원 : 120명 (30팀)",
            prize = "총상금 : 1000 만원"
        ),
        onMenuClick = {}
    )
}