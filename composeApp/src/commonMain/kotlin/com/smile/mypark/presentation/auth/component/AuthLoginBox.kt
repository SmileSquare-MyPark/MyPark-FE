package com.smile.mypark.presentation.auth.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.smile.mypark.ui.theme.NanumTypography
import com.smile.mypark.ui.theme.Primary
import com.smile.mypark.ui.theme.White
import mypark.composeapp.generated.resources.Res
import mypark.composeapp.generated.resources.login
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun MyparkLoginButton(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    RoundedRect7(
        modifier = modifier
            .fillMaxWidth()
            .height(52.dp),
        containerColor = Primary,
        contentColor = White,
        centerContent = true,
        onClick = onClick
    ) {
        Text(text = text, style = NanumTypography().titleMediumB, fontSize = 20.sp)
    }
}

@Preview
@Composable
fun MyparkLoginButtonPreview() {
    MyparkLoginButton(text = stringResource(Res.string.login)) {

    }
}