package com.smile.mypark.presentation.my.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.smile.mypark.core.ext.toFixedSp
import com.smile.mypark.core.ui.theme.CharcoalGray
import com.smile.mypark.core.ui.theme.MediumLightGray
import com.smile.mypark.core.ui.theme.Primary
import com.smile.mypark.core.ui.theme.White
import mypark.composeapp.generated.resources.Res
import mypark.composeapp.generated.resources.ic_test
import mypark.composeapp.generated.resources.ic_triangle_gray
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun MyMenuItemCard(
    title: String,
    leading: @Composable () -> Unit,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(10.dp),
        color = White,
        shadowElevation = 2.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            leading()

            Spacer(Modifier.width(12.dp))

            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = 18.toFixedSp(),
                    color = CharcoalGray
                )
            )
        }
    }
}

@Composable
fun PersonalSettingItem(
    title: String,
    value: String? = null,
    valueColor: Color = CharcoalGray,
    showChevron: Boolean = false,
    onClick: () -> Unit,
    trailing: @Composable (() -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(10.dp),
        color = White,
        shadowElevation = 2.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = 16.toFixedSp(),
                    color = CharcoalGray
                )
            )

            Spacer(Modifier.weight(1f))

            when {
                trailing != null -> trailing()
                value != null -> Text(
                    text = value,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontSize = 15.toFixedSp(),
                        color = valueColor
                    )
                )
            }

            if (showChevron) {
                Spacer(Modifier.width(6.dp))
                Icon(
                    painter = painterResource(Res.drawable.ic_triangle_gray), // 삼각형 아이콘
                    contentDescription = null,
                    tint = MediumLightGray,
                    modifier = Modifier.size(14.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewPersonalSettingItem() {
    PersonalSettingItem(
        title = "닉네임 설정",
        value = "가나다",
        valueColor = Primary,
        showChevron = false,
        onClick = {

        }
    )
}

@Preview
@Composable
fun PreviewResultMenuItemCard() {
    MyMenuItemCard(
        title = "QR 로그인",
        leading = {
            Icon(
                painter = painterResource(Res.drawable.ic_test),
                contentDescription = "라운드 결과",
                tint = Primary,
                modifier = Modifier.size(32.dp)
            )
        },
        onClick = {

        }
    )
}