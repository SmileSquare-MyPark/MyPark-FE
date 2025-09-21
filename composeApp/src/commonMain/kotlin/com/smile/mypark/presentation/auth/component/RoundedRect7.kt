package com.smile.mypark.presentation.auth.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.smile.mypark.core.ui.theme.Black
import com.smile.mypark.core.ui.theme.Gray
import com.smile.mypark.core.ui.theme.LightGray179
import com.smile.mypark.core.ui.theme.Primary
import kotlinx.serialization.json.JsonNull.content
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
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    borderColor: Color = Black,
    borderWidth: Dp = 0.1.dp,
    textStyle: TextStyle
) {
    RoundedRect7(
        modifier = modifier,
        containerColor = Color.White,
        border = BorderStroke(borderWidth, borderColor),
        padding = PaddingValues()
    ) {
        TextField(
            value = value,
            onValueChange = onValueChange,
            textStyle = textStyle,
            placeholder = {
                Text(
                    text = placeholder,
                    color = LightGray179,
                    style = MaterialTheme.typography.bodyLarge
                )
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                errorContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent
            ),
            singleLine = true
        )
    }
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
