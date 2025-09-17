package com.smile.mypark.core.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.smile.mypark.core.ui.theme.White

@Composable
fun RoundShape(
    backgroundColor: Color = White,
    boarderColor: Color? = null,
    corner: Dp = 16.dp,
    contentAlignment: Alignment = Alignment.TopStart,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    val shape = RoundedCornerShape(corner)

    Box(
        modifier = modifier
            .then(if (boarderColor != null) Modifier.border(1.dp, boarderColor, shape) else Modifier)
            .background(backgroundColor, shape),
        contentAlignment = contentAlignment
    ) {
        content()
    }

}