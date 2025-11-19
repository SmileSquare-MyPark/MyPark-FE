package com.smile.mypark.presentation.contest

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.smile.mypark.core.ext.toFixedSp
import com.smile.mypark.core.ui.theme.Gray160
import com.smile.mypark.core.ui.theme.Primary
import com.smile.mypark.core.ui.theme.White
import mypark.composeapp.generated.resources.Res
import mypark.composeapp.generated.resources.ic_location_black
import mypark.composeapp.generated.resources.ic_trophy
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun ContestJoinedPage(
    modifier: Modifier = Modifier
) {
    val dummyList = listOf(
        ContestJoinedItemUi(
            title = "브라보 시흥 배곧점 매장 대회",
            dateRange = "2025.00.00.am9 ~ 2025.00.00.pm6",
            regionName = "화천군",
            score = 73,
            rank = 15,
            totalPlayers = 317,
            themeColor = Primary,
        ),
        ContestJoinedItemUi(
            title = "마이파크 연천점 오픈 매장 대회",
            dateRange = "2025.00.00.am9 ~ 2025.00.00.pm6",
            regionName = "화천군",
            score = 84,
            rank = 30,
            totalPlayers = 100,
            themeColor = Color(0xFF9B4EC8),
        ),
        ContestJoinedItemUi(
            title = "전국 스크린 파크골프 대회",
            dateRange = "2025.00.00.am9 ~ 2025.00.00.pm6",
            regionName = "화천군",
            score = 90,
            rank = 15,
            totalPlayers = 120,
            themeColor = Primary
        )
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(White)
            .padding(horizontal = 14.dp, vertical = 20.dp)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(vertical = 4.dp)
        ) {
            items(dummyList) { item ->
                ContestJoinedItemCard(
                    item = item,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Composable
private fun ContestJoinedItemCard(
    item: ContestJoinedItemUi,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(11.dp),
        color = item.themeColor,
        tonalElevation = 2.dp,
        shadowElevation = 4.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp, horizontal = 10.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(Res.drawable.ic_trophy),
                    contentDescription = null,
                    tint = Color.Yellow,
                    modifier = Modifier.size(32.dp)
                )

                Spacer(Modifier.width(11.dp))

                Text(
                    text = item.title,
                    style = MaterialTheme.typography.headlineLarge.copy(
                        fontSize = 20.toFixedSp(),
                        color = White
                    ),
                    modifier = Modifier.weight(1f)
                )

                Text(
                    text = "참여중",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontSize = 12.toFixedSp(),
                        color = Color(0xFFB2FF17)
                    )
                )
            }

            Spacer(Modifier.height(10.dp))

            Surface(
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(topStart = 5.dp, topEnd = 5.dp),
                color = Color(0xFFEDEDED)
            ) {
                Text(
                    text = item.dateRange,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontSize = 15.toFixedSp(),
                        color = Color(0xFF5F5F5F)
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp),
                    textAlign = TextAlign.Center
                )
            }

            Surface(
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(bottomStart = 5.dp, bottomEnd = 5.dp),
                color = White
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 7.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Spacer(Modifier.width(10.dp))
                    Surface(
                        modifier = Modifier
                            .size(70.dp)
                            .border(1.dp, Gray160, RoundedCornerShape(6.dp)),
                        color = White,
                        shadowElevation = 1.dp
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(Res.drawable.ic_location_black), // 지역 로고
                                contentDescription = null,
                                modifier = Modifier.size(50.dp)
                            )
                        }
                    }

                    Spacer(Modifier.width(16.dp))

                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 15.dp),
                        horizontalAlignment = Alignment.End
                    ) {
                        Row(
                            verticalAlignment = Alignment.Bottom,
                        ) {
                            Text(
                                text = item.score.toString(),
                                style = MaterialTheme.typography.headlineLarge.copy(
                                    fontSize = 45.toFixedSp(),
                                    lineHeight = 50.toFixedSp(),
                                    color = Color(0xFF01668C)
                                )
                            )
                            Spacer(Modifier.width(5.dp))
                            Text(
                                text = "타",
                                style = MaterialTheme.typography.bodyLarge.copy(
                                    fontSize = 20.toFixedSp(),
                                    color = Color(0xFF01668C)
                                ),
                                modifier = Modifier.padding(bottom = 5.dp)
                            )
                        }
                        Spacer(Modifier.height(5.dp))
                        Text(
                            text = "${item.rank} 등 / ${item.totalPlayers} 명",
                            style = MaterialTheme.typography.headlineLarge.copy(
                                fontSize = 20.toFixedSp(),
                                lineHeight = 25.toFixedSp(),
                                color = Color(0xFF515151)
                            )
                        )
                    }
                }
            }
        }
    }
}

private data class ContestJoinedItemUi(
    val title: String,
    val dateRange: String,
    val regionName: String,
    val score: Int,
    val rank: Int,
    val totalPlayers: Int,
    val themeColor: Color
)

@Preview
@Composable
private fun ContestJoinedPagePreview() {
    ContestJoinedPage()
}