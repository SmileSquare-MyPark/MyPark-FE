package com.smile.mypark.presentation.auth.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.smile.mypark.core.ui.theme.Primary
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun RoundedRect7(
    modifier: Modifier = Modifier,
    containerColor: Color = MaterialTheme.colorScheme.surface,
    contentColor: Color = contentColorFor(containerColor),
    border: BorderStroke? = null,
    shadowElevation: Dp = 0.dp,
    padding: PaddingValues = PaddingValues(vertical = 15.dp),
    centerContent: Boolean = false,
    onClick: (() -> Unit)? = null,
    content: @Composable () -> Unit
) {
    val shape = RoundedCornerShape(7.dp)

    Surface(
        modifier = modifier,
        shape = shape,
        color = containerColor,
        contentColor = contentColor,
        border = border,
        shadowElevation = shadowElevation,
        onClick = onClick ?: {}
    ) {
        val boxModifier = Modifier.padding(padding)
        if (centerContent) {
            Box(boxModifier, contentAlignment = Alignment.Center) { content() }
        } else {
            Box(boxModifier) { content() }
        }
    }
}

@Composable
fun BorderedRoundedRect7(
    modifier: Modifier = Modifier,
    borderColor: Color = Color.Black,
    borderWidth: Dp = 1.dp,
    content: @Composable () -> Unit
) {
    RoundedRect7(
        modifier = modifier,
        containerColor = Color.White,
        border = BorderStroke(borderWidth, borderColor),
        padding = PaddingValues(16.dp),
        content = content
    )
}

@Preview
@Composable
fun RoundedRect7Preview() {
    RoundedRect7(
        containerColor = Primary,
        padding = PaddingValues(16.dp),
    ) {
        Text("Hello")
    }
}
