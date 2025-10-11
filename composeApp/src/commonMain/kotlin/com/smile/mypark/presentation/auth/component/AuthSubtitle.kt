package com.smile.mypark.presentation.auth.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.smile.mypark.core.ext.toFixedSp
import com.smile.mypark.core.ui.theme.Primary
import mypark.composeapp.generated.resources.Res
import mypark.composeapp.generated.resources.login_main_title
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun AuthSubtitle(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleLarge.copy(fontSize = 20.toFixedSp(), lineHeight = 23.toFixedSp()),
        color = Primary,
        fontWeight = FontWeight.ExtraBold,
        modifier = modifier
    )
}

@Preview
@Composable
fun AuthSubtitlePreview() {
    AuthSubtitle(stringResource(Res.string.login_main_title))
}