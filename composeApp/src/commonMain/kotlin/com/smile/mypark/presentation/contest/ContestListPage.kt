package com.smile.mypark.presentation.contest

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import com.smile.mypark.core.ui.theme.Black
import com.smile.mypark.core.ui.theme.DarkGray
import com.smile.mypark.core.ui.theme.MediumLightGray
import com.smile.mypark.core.ui.theme.Primary
import com.smile.mypark.core.ui.theme.VeryLightGray242
import com.smile.mypark.core.ui.theme.White
import mypark.composeapp.generated.resources.Res
import mypark.composeapp.generated.resources.ic_trophy
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun ContestListPage(
    modifier: Modifier = Modifier,
    onClickContest: (ContestListItemUi) -> Unit = {}
) {
    val dummyList = listOf(
        ContestListItemUi(1,"[진행]", "브라보 시흥 배곧점 매장 대회", "25.00.00 ~ 25.00.00", true),
        ContestListItemUi(2,"[진행]", "마이파크 연천점 오픈 매장 대회", "25.00.00 ~ 25.00.00", true),
        ContestListItemUi(3,"[진행]", "스마일스퀘어 전국 대회", "25.00.00 ~ 25.00.00", true),
        ContestListItemUi(4,"[진행]", "마이파크 ○○점 오픈 매장 대회", "25.00.00 ~ 25.00.00", true),
        ContestListItemUi(5,"[진행]", "제 3회 전국 스크린 파크골프", "25.00.00 ~ 25.00.00", true),
        ContestListItemUi(6,"[종료]", "마이파크 ○○점 오픈 매장 대회", "24.00.00 ~ 24.00.00", false),
        ContestListItemUi(7,"[종료]", "마이파크 ○○점 오픈 매장 대회", "24.00.00 ~ 24.00.00", false),
        ContestListItemUi(8,"[종료]", "마이파크 ○○점 오픈 매장 대회", "24.00.00 ~ 24.00.00", false),
        ContestListItemUi(9,"[종료]", "마이파크 ○○점 오픈 매장 대회", "24.00.00 ~ 24.00.00", false),
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(White)
            .padding(horizontal = 14.dp, vertical = 20.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(Res.drawable.ic_trophy),
                contentDescription = null,
                tint = Primary,
                modifier = Modifier.size(32.dp)
            )
            Spacer(Modifier.width(8.dp))
            Text(
                text = "대회 목록",
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = 20.toFixedSp(),
                    color = Black
                )
            )
        }

        Spacer(Modifier.height(12.dp))

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            shape = RoundedCornerShape(15.dp),
            color = VeryLightGray242
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(vertical = 20.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                items(dummyList) { item ->
                    ContestListItem(
                        item = item,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 11.dp),
                        onClick = { onClickContest(item) }
                    )
                }
            }
        }
    }
}

@Composable
private fun ContestListItem(
    item: ContestListItemUi,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .clickable(onClick = onClick),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = item.statusText,
            style = MaterialTheme.typography.headlineLarge.copy(
                fontSize = 10.toFixedSp(),
                color = if (item.isOngoing) Primary else Color(0xFFFF6666)
            )
        )

        Spacer(Modifier.width(4.dp))

        Text(
            text = item.title,
            style = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 14.toFixedSp(),
                color = DarkGray
            ),
            modifier = Modifier
                .weight(1f)
                .padding(end = 8.dp),
            textAlign = TextAlign.Start
        )

        Text(
            text = item.dateRange,
            style = MaterialTheme.typography.bodySmall.copy(
                fontSize = 12.toFixedSp(),
                color = MediumLightGray
            )
        )
    }
}

data class ContestListItemUi(
    val id: Long,
    val statusText: String,
    val title: String,
    val dateRange: String,
    val isOngoing: Boolean
)

@Preview
@Composable
private fun ContestListPagePreview() {
    ContestListPage()
}
