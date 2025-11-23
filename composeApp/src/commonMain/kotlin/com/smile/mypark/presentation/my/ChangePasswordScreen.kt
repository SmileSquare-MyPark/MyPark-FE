package com.smile.mypark.presentation.my

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.smile.mypark.core.ext.keyboardHide
import com.smile.mypark.core.ext.toFixedSp
import com.smile.mypark.core.ui.component.Topbar
import com.smile.mypark.core.ui.theme.Black
import com.smile.mypark.core.ui.theme.NeutralGray
import com.smile.mypark.core.ui.theme.White
import com.smile.mypark.presentation.auth.component.BorderedRoundedRect7
import com.smile.mypark.presentation.auth.component.MyparkLoginButton
import com.smile.mypark.presentation.main.navigation.MainNavigator
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
internal fun ChangePasswordRoute(
    padding: PaddingValues,
    navigator: MainNavigator
) {
    ChangePasswordScreen(
        padding = padding,
        onClickBack = { navigator.navigateUp() }
    )
}
@Composable
fun ChangePasswordScreen(
    padding: PaddingValues,
    onClickBack: () -> Unit = {},
    onClickConfirm: (oldPw: String, newPw: String, confirmPw: String) -> Unit = { _, _, _ -> },
    modifier: Modifier = Modifier
) {
    var oldPw by remember { mutableStateOf("") }
    var newPw by remember { mutableStateOf("") }
    var confirmPw by remember { mutableStateOf("") }

    val isReady = oldPw.isNotBlank() && newPw.isNotBlank() &&
            confirmPw.isNotBlank() && newPw == confirmPw

    Column(
        modifier
            .fillMaxSize()
            .background(White)
            .padding(padding)
            .padding(top = 40.dp, bottom = 80.dp)
            .keyboardHide(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Topbar(
            title = "비밀번호 설정",
            onClick = onClickBack
        )

        Spacer(Modifier.height(40.dp))

        Text(
            text = "비밀번호를 변경해주세요",
            style = MaterialTheme.typography.headlineLarge.copy(
                fontSize = 20.toFixedSp(),
                lineHeight = 23.toFixedSp(),
                color = Black
            )
        )

        Spacer(Modifier.height(12.dp))

        Text(
            text = "비밀번호를 재설정 해주세요.",
            style = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 14.toFixedSp(),
                lineHeight = 16.toFixedSp(),
                color = NeutralGray
            ),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 40.dp)
        )

        Spacer(Modifier.height(40.dp))

        Text(
            text = "기존 비밀번호 입력",
            style = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 15.toFixedSp(),
                lineHeight = 17.toFixedSp(),
                color = NeutralGray
            )
        )

        Spacer(Modifier.height(12.dp))

        BorderedRoundedRect7(
            value = oldPw,
            onValueChange = { oldPw = it },
            placeholder = "기존 비밀번호를 입력해주세요",
            textStyle = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 13.toFixedSp(),
                lineHeight = 17.toFixedSp(),
                color = Black
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Next
            ),
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(horizontal = 40.dp)
        )

        Spacer(Modifier.height(28.dp))

        Text(
            text = "새로운 비밀번호",
            style = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 15.toFixedSp(),
                lineHeight = 17.toFixedSp(),
                color = NeutralGray
            )
        )

        Spacer(Modifier.height(12.dp))

        BorderedRoundedRect7(
            value = newPw,
            onValueChange = { newPw = it },
            placeholder = "새로운 비밀번호를 입력해주세요",
            textStyle = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 13.toFixedSp(),
                lineHeight = 17.toFixedSp(),
                color = Black
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Next
            ),
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(horizontal = 40.dp)
        )

        Spacer(Modifier.height(8.dp))

        BorderedRoundedRect7(
            value = confirmPw,
            onValueChange = { confirmPw = it },
            placeholder = "비밀번호 확인",
            textStyle = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 13.toFixedSp(),
                lineHeight = 17.toFixedSp(),
                color = Black
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(horizontal = 40.dp)
        )

        Spacer(Modifier.weight(1f))

        MyparkLoginButton(
            text = "확인",
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(horizontal = 40.dp),
            onClick = { onClickConfirm(oldPw, newPw, confirmPw) },
            enabled = isReady
        )
    }
}

@Preview
@Composable
private fun ChangePasswordScreenPreview() {
    ChangePasswordScreen(
        padding = PaddingValues()
    )
}
