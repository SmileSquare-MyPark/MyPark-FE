package com.smile.mypark.core.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.smile.mypark.core.ext.toFixedSp
import com.smile.mypark.core.ui.theme.Gray50
import com.smile.mypark.core.ui.theme.Gray60
import com.smile.mypark.core.ui.theme.Gray80
import com.smile.mypark.core.ui.theme.Primary

@Composable
fun ScoreItem(
    score: String,
    unit: String = "",
    title: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier.padding(horizontal = 8.dp)
    ) {
        Text(
            title,
            style = MaterialTheme.typography.bodySmall.copy(fontSize = 10.toFixedSp(), lineHeight = 21.toFixedSp()),
            color = Gray60
        )
        Row(
            Modifier.weight(1f),
            verticalAlignment = Alignment.Bottom
        ) {
            Text(
                score,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = 28.toFixedSp(),
                    lineHeight = 29.toFixedSp()
                ),
                color = Primary
            )
            Text(
                unit,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = 24.toFixedSp(),
                    lineHeight = 29.toFixedSp()
                ),
                color = Primary
            )
        }
    }
}

@Composable
fun NotiItem(
    title: String,
    dueDate: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier.padding(horizontal = 12.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            title,
            style = MaterialTheme.typography.bodySmall.copy(fontSize = 14.toFixedSp()),
            color = Gray80,
            modifier = modifier.weight(1f)
        )
        Text(
            dueDate,
            style = MaterialTheme.typography.bodyLarge.copy(fontSize = 12.toFixedSp()),
            color = Gray50
        )
    }
}