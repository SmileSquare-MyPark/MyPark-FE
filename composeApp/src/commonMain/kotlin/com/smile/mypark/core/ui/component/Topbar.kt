package com.smile.mypark.core.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImagePainter.State.Empty.painter
import com.smile.mypark.core.ext.noRippleSingleClickable
import com.smile.mypark.core.ext.toFixedSp
import com.smile.mypark.core.ui.theme.NeutralGray
import com.smile.mypark.core.ui.theme.Primary
import com.smile.mypark.core.ui.theme.White
import mypark.composeapp.generated.resources.Res
import mypark.composeapp.generated.resources.agreement
import mypark.composeapp.generated.resources.ic_back
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun Topbar(
    title: String,
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(Res.drawable.ic_back),
            contentDescription = null,
            modifier = Modifier
                .noRippleSingleClickable { onClick() }
                .align(Alignment.CenterStart)
                .size(22.dp)

        )
        Text(
            text = title,
            style = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 15.toFixedSp(),
                lineHeight = 17.toFixedSp(),
                fontWeight = FontWeight(700)
            ),
            color = NeutralGray
        )
    }
}
@Preview
@Composable
fun PreviewTopbar(){
    Topbar(
        title = stringResource(Res.string.agreement),
        onClick = {}
    )

}