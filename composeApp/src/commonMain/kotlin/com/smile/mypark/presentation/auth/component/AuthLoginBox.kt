package com.smile.mypark.presentation.auth.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.smile.mypark.core.ext.toFixedSp
import com.smile.mypark.core.ui.theme.Sub
import com.smile.mypark.core.ui.theme.White
import mypark.composeapp.generated.resources.Res
import mypark.composeapp.generated.resources.login
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun MyparkLoginButton(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    enabled: Boolean
) {
    RoundedRect7(
        modifier = modifier
            .fillMaxWidth()
            .height(47.dp),
        containerColor = Sub,
        contentColor = White,
        centerContent = true,
        onClick = onClick
    ) {
        Text(text = text, style = MaterialTheme.typography.bodyLarge.copy(fontSize = 18.toFixedSp(), lineHeight = 20.toFixedSp(), color = White))
    }
}

@Preview
@Composable
fun MyparkLoginButtonPreview() {
    MyparkLoginButton(
        text = stringResource(Res.string.login),
        onClick = { },
        enabled = true
    )
}