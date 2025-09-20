package com.smile.mypark.presentation.auth

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.smile.mypark.core.ext.noRippleSingleClickable
import com.smile.mypark.core.ext.toFixedSp
import com.smile.mypark.core.ui.component.CustomRadioButton
import com.smile.mypark.core.ui.theme.LightGray179
import com.smile.mypark.core.ui.theme.NeutralGray
import com.smile.mypark.domain.model.Dummy
import com.smile.mypark.presentation.auth.component.AuthSubtitle
import com.smile.mypark.presentation.auth.component.BorderedRoundedRect7
import com.smile.mypark.presentation.auth.component.MyParkLogo
import com.smile.mypark.presentation.auth.component.MyparkLoginButton
import com.smile.mypark.presentation.auth.component.SocialCircleIcon
import mypark.composeapp.generated.resources.Res
import mypark.composeapp.generated.resources.copyright
import mypark.composeapp.generated.resources.ic_radio_off
import mypark.composeapp.generated.resources.ic_radio_on
import mypark.composeapp.generated.resources.login
import mypark.composeapp.generated.resources.login_comfortable
import mypark.composeapp.generated.resources.login_id
import mypark.composeapp.generated.resources.login_main_title
import mypark.composeapp.generated.resources.login_pw
import mypark.composeapp.generated.resources.login_warning
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
internal fun LoginRoute(
    padding: PaddingValues,
    onClickLogin: () -> Unit,
    onClickSignUp: () -> Unit = {},
    onClickFindIdPw: () -> Unit = {},
) {
    LoginScreen(
        padding = padding,
        onClickLogin = onClickLogin,
        onClickSignUp = onClickSignUp,
        onClickFindIdPw = onClickFindIdPw
    )
}

@Composable
private fun LoginScreen(
    padding: PaddingValues,
    onClickLogin: () -> Unit,
    onClickSignUp: () -> Unit,
    onClickFindIdPw: () -> Unit,
) {
    var autoLogin by rememberSaveable { mutableStateOf(false) }
    var autoIdLogin by rememberSaveable { mutableStateOf(false) }
    val toggleAutoLogin = { autoLogin = !autoLogin }
    val toggleAutoIdLogin = { autoIdLogin = !autoIdLogin }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
            .windowInsetsPadding(WindowInsets.systemBars)
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(horizontal = 24.dp)
                .widthIn(max = 360.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MyParkLogo(Modifier.widthIn(max = 220.dp))

            Spacer(Modifier.height(21.dp))

            Text(
                text = stringResource(Res.string.login_warning),
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = 14.toFixedSp(),
                    lineHeight = 16.toFixedSp()
                ),
                color = NeutralGray
            )

            Spacer(Modifier.height(13.dp))

            var id by remember { mutableStateOf("") }
            var pw by remember { mutableStateOf("") }

            BorderedRoundedRect7(
                value = id,
                onValueChange = { id = it },
                placeholder = stringResource(Res.string.login_id),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(horizontal = 30.dp)
            )

            Spacer(Modifier.height(5.dp))

            BorderedRoundedRect7(
                value = pw,
                onValueChange = { pw = it },
                placeholder = stringResource(Res.string.login_pw),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(horizontal = 30.dp)
            )

            Spacer(Modifier.height(7.dp))

            MyparkLoginButton(
                text = stringResource(Res.string.login),
                onClick = onClickLogin,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(horizontal = 30.dp)
            )

            Spacer(Modifier.height(11.dp))

            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 30.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.noRippleSingleClickable { toggleAutoLogin }
                ) {
                    CustomRadioButton(
                        selected = autoLogin,
                        onClick = toggleAutoLogin,
                        selectedIcon = Res.drawable.ic_radio_on,
                        unselectedIcon = Res.drawable.ic_radio_off,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(Modifier.width(5.dp))
                    Text(
                        text = "자동 로그인",
                        style = MaterialTheme.typography.bodyLarge.copy(fontSize = 13.toFixedSp(), lineHeight = 15.toFixedSp()),
                        color = LightGray179
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.noRippleSingleClickable { toggleAutoIdLogin() }
                ) {
                    CustomRadioButton(
                        selected = autoIdLogin,
                        onClick = toggleAutoIdLogin,
                        selectedIcon = Res.drawable.ic_radio_on,
                        unselectedIcon = Res.drawable.ic_radio_off,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(Modifier.width(5.dp))
                    Text(
                        text = "아이디 자동 로그인",
                        style = MaterialTheme.typography.bodyLarge.copy(fontSize = 13.toFixedSp(), lineHeight = 15.toFixedSp()),
                        color = LightGray179
                    )
                }
            }

            Spacer(Modifier.height(20.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 70.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "회원가입",
                    style = MaterialTheme.typography.bodyLarge.copy(fontSize = 13.toFixedSp(), lineHeight = 15.toFixedSp()),
                    color = LightGray179,
                    modifier = Modifier.clickable { onClickSignUp() }
                )
                Text(
                    text = "아이디/비밀번호 찾기",
                    style = MaterialTheme.typography.bodyLarge.copy(fontSize = 13.toFixedSp(), lineHeight = 15.toFixedSp()),
                    color = LightGray179,
                    modifier = Modifier.clickable { onClickFindIdPw() }
                )
            }

            Spacer(Modifier.height(50.dp))


        }
    }
}

@Preview
@Composable
private fun PreviewLogin(){
    LoginScreen(
        padding = PaddingValues(),
        onClickLogin = {},
        onClickSignUp = {},
        onClickFindIdPw = {}
    )
}