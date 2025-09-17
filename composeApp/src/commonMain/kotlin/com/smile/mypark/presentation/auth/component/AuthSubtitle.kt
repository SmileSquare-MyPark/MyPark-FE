package com.smile.mypark.presentation.auth.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key.Companion.R
import androidx.compose.ui.unit.sp
import com.smile.mypark.ui.theme.NanumTypography
import com.smile.mypark.ui.theme.Primary
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
        style = NanumTypography().headlineLargeEB,
        color = Primary,
        fontSize = 22.sp,
        modifier = modifier
    )
}

@Preview
@Composable
fun AuthSubtitlePreview() {
    AuthSubtitle(stringResource(Res.string.login_main_title))
}