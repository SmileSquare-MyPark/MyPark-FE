package com.smile.mypark.presentation.auth.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun SocialButtonsRow(
    modifier: Modifier = Modifier,
    onClickKakao: () -> Unit,
    onClickNaver: () -> Unit
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        SocialCircleIcon(
            bg = Color(0xFFFEE500),
            onClick = onClickKakao
        ) { Text("K") }

        SocialCircleIcon(
            bg = Color(0xFF03C75A),
            onClick = onClickNaver
        ) { Text("N", color = Color.White) }
    }
}

@Composable
fun SocialCircleIcon(
    bg: Color,
    onClick: () -> Unit,
    content: @Composable BoxScope.() -> Unit
) {
    RoundedRect7(
        modifier = Modifier.size(48.dp),
        containerColor = bg,
        contentColor = Color.Unspecified,
        centerContent = true,
        onClick = onClick,
        padding = PaddingValues(0.dp)
    ) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center, content = content)
    }
}

@Preview
@Composable
fun SocialButtonsRowPreview() {
    SocialButtonsRow(
        onClickKakao = {},
        onClickNaver = {}
    )
}

