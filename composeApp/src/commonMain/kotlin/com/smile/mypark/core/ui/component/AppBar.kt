package com.smile.mypark.core.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.smile.mypark.core.ext.noRippleSingleClickable
import com.smile.mypark.core.ui.theme.Gray10
import com.smile.mypark.core.ui.theme.Primary
import com.smile.mypark.core.ui.theme.White
import mypark.composeapp.generated.resources.Res
import mypark.composeapp.generated.resources.ic_test
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun LogoAppBar(
    reverseColor: Boolean = false,
    onClick: () -> Unit = {},
    rightRes : DrawableResource? = Res.drawable.ic_test
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(if (reverseColor) Primary else White)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painterResource(Res.drawable.ic_test),
            null,
            tint = if (reverseColor) White else Primary,
            modifier = Modifier.size(48.dp)
        )
        Spacer(Modifier.weight(1f))
        if (rightRes != null){
            Icon(
                painterResource(rightRes),
                null,
                tint = if (reverseColor) White else Primary,
            )
        }
    }
}

@Composable
fun AppBar(
    onBack: () -> Unit,
    onClick: () -> Unit = {},
    title: String,
    rightRes : DrawableResource? = null
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(White)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painterResource(Res.drawable.ic_test),
            null,
            tint = Primary,
            modifier = Modifier.noRippleSingleClickable { onBack() }
        )
        Spacer(Modifier.width(4.dp))
        Text(
            title,
            style = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 25.sp,
                lineHeight = 29.sp,
                fontWeight = FontWeight(700)
            ),
            color = Primary
        )
        Spacer(Modifier.weight(1f))
        if (rightRes != null)
            Icon(
                painterResource(rightRes),
                null,
                tint = Primary,
                modifier = Modifier.noRippleSingleClickable{onClick()}
            )
    }
}

@Preview
@Composable
fun PreviewAppBar() {
    Column(
        Modifier.background(Gray10)
    ) {
        LogoAppBar()
        Spacer(Modifier.height(16.dp))
        AppBar({}, {},"설정", )
    }
}