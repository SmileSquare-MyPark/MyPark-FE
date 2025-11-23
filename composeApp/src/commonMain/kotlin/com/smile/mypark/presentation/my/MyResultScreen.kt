package com.smile.mypark.presentation.my

import MyParkTopBar
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.smile.mypark.core.ext.toFixedSp
import com.smile.mypark.core.ui.theme.BackgroundGray
import com.smile.mypark.core.ui.theme.Primary
import com.smile.mypark.core.ui.theme.Sub
import com.smile.mypark.core.ui.theme.White
import com.smile.mypark.presentation.my.component.MyMenuItemCard
import mypark.composeapp.generated.resources.Res
import mypark.composeapp.generated.resources.ic_qr_green
import mypark.composeapp.generated.resources.ic_result
import mypark.composeapp.generated.resources.ic_trophy
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun MyResultScreen(
    padding: PaddingValues,
    nickname: String = "가나다 님",
    modifier: Modifier = Modifier
) {
    Column(
        modifier
            .fillMaxSize()
            .background(BackgroundGray)
            .padding(padding)
    ) {
        MyParkTopBar(
            onMenuClick = { /* TODO: 메뉴 클릭 */ },
            backgroundColor = Primary,
            menuTint = White,
            logoTint = White
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.TopCenter),
                color = Primary,
                shape = RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 20.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Text(
                        text = nickname,
                        style = MaterialTheme.typography.headlineLarge.copy(
                            fontSize = 30.toFixedSp(),
                            color = White
                        )
                    )
                }
            }
        }

        Spacer(Modifier.height(26.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            MyMenuItemCard(
                title = "QR 로그인",
                leading = {
                    Icon(
                        painter = painterResource(Res.drawable.ic_qr_green),
                        contentDescription = "QR 로그인",
                        tint = Primary,
                        modifier = Modifier.size(32.dp)
                    )
                },
                onClick = { /* TODO: QR 로그인 */ }
            )

            MyMenuItemCard(
                title = "연습 훈련 결과",
                leading = {
                    Icon(
                        painter = painterResource(Res.drawable.ic_result),
                        contentDescription = "연습 훈련 결과",
                        tint = Primary,
                        modifier = Modifier.size(32.dp)
                    )
                },
                onClick = { /* TODO: 연습 훈련 결과 */ }
            )

            MyMenuItemCard(
                title = "라운드 결과",
                leading = {
                    Icon(
                        painter = painterResource(Res.drawable.ic_result),
                        contentDescription = "라운드 결과",
                        tint = Primary,
                        modifier = Modifier.size(32.dp)
                    )
                },
                onClick = { /* TODO: 라운드 결과 */ }
            )

            MyMenuItemCard(
                title = "대회",
                leading = {
                    Icon(
                        painter = painterResource(Res.drawable.ic_trophy),
                        contentDescription = "대회",
                        tint = Primary,
                        modifier = Modifier.size(32.dp)
                    )
                },
                onClick = { /* TODO: 대회 */ }
            )
        }

        Spacer(Modifier.weight(1f))

        Button(
            onClick = { /* TODO: 로그아웃 */ },
            colors = ButtonDefaults.buttonColors(
                containerColor = Sub,
                contentColor = White
            ),
            shape = RoundedCornerShape(7.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp, vertical = 24.dp)
                .height(48.dp)
        ) {
            Text(
                text = "로그아웃",
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontSize = 16.toFixedSp(),
                    color = White
                )
            )
        }
    }
}

@Preview
@Composable
private fun MyResultScreenPreview() {
    MyResultScreen(
        padding = PaddingValues()
    )
}

