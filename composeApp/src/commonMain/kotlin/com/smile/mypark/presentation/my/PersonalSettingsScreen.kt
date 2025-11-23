package com.smile.mypark.presentation.my

import MyParkTopBar
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.smile.mypark.core.ext.toFixedSp
import com.smile.mypark.core.ui.theme.BackgroundGray
import com.smile.mypark.core.ui.theme.Black
import com.smile.mypark.core.ui.theme.MediumLightGray
import com.smile.mypark.core.ui.theme.Primary
import com.smile.mypark.core.ui.theme.White
import com.smile.mypark.presentation.my.component.PersonalSettingItem
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun PersonalSettingsScreen(
    padding: PaddingValues,
    modifier: Modifier = Modifier,
    onMenuClick: () -> Unit = {},
) {
    Column(
        modifier
            .fillMaxSize()
            .background(BackgroundGray)
            .padding(padding)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .align(Alignment.TopCenter),
                shape = RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp),
                color = White,
                shadowElevation = 5.dp

            ) {
                MyParkTopBar(
                    onMenuClick = onMenuClick,
                    backgroundColor = White,
                    menuTint = Primary,
                    logoTint = White,
                    logo = {
                        Text(
                            text = "스크린 개인 설정",
                            style = MaterialTheme.typography.headlineLarge.copy(
                                fontSize = 25.toFixedSp(),
                                color = Primary
                            ),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth().padding(start = 35.dp)
                        )
                    }
                )
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontSize = 25.toFixedSp(),
                            color = Black
                        ),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }

        Spacer(Modifier.height(12.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 12.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            // 닉네임 설정
            PersonalSettingItem(
                title = "닉네임 설정",
                value = "가나다",
                valueColor = Primary,
                showChevron = false,
                onClick = { /* TODO: 닉네임 설정 이동 */ }
            )

            // 난이도
            PersonalSettingItem(
                title = "난이도",
                showChevron = true,
                trailing = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "★★★★",
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontSize = 16.toFixedSp(),
                                color = Primary
                            )
                        )
                    }
                },
                onClick = { /* TODO: 난이도 선택 */ }
            )

            // TEE 높이
            PersonalSettingItem(
                title = "TEE 높이",
                value = "높음",
                valueColor = Primary,
                showChevron = true,
                onClick = { /* TODO: TEE 높이 선택 */ }
            )

            // 스윙플레이트
            PersonalSettingItem(
                title = "스윙플레이트",
                showChevron = true,
                onClick = { /* TODO: 스윙플레이트 선택 */ }
            )

            // 컨시드 (스위치)
            PersonalSettingItem(
                title = "컨시드",
                onClick = {  },
                trailing = {
                    var checked by remember { mutableStateOf(true) }
                    Switch(
                        checked = checked,
                        onCheckedChange = { checked = it },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = White,
                            checkedTrackColor = Primary,
                            uncheckedThumbColor = White,
                            uncheckedTrackColor = MediumLightGray
                        )
                    )
                }
            )

            // 코스 스피드
            PersonalSettingItem(
                title = "코스 스피드",
                value = "느림",
                valueColor = Primary,
                showChevron = true,
                onClick = { /* TODO */ }
            )

            // 그린 스피드
            PersonalSettingItem(
                title = "그린 스피드",
                value = "느림",
                valueColor = Primary,
                showChevron = true,
                onClick = { /* TODO */ }
            )

            // 멀리건
            PersonalSettingItem(
                title = "멀리건",
                value = "없음",
                valueColor = Primary,
                showChevron = true,
                onClick = { /* TODO */ }
            )

            // 홀컴
            PersonalSettingItem(
                title = "홀컴",
                showChevron = true,
                onClick = { /* TODO */ }
            )
        }
    }
}


@Preview
@Composable
private fun PersonalSettingsScreenPreview() {
    PersonalSettingsScreen(
        padding = PaddingValues(),
        onMenuClick = {}
    )
}