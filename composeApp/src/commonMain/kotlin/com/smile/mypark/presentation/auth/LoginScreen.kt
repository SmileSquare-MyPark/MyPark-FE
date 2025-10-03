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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.smile.mypark.core.ext.keyboardHide
import com.smile.mypark.core.ext.noRippleSingleClickable
import com.smile.mypark.core.ext.toFixedSp
import com.smile.mypark.core.ui.component.CustomRadioButton
import com.smile.mypark.core.ui.theme.Black
import com.smile.mypark.core.ui.theme.LightGray179
import com.smile.mypark.core.ui.theme.NeutralGray
import com.smile.mypark.presentation.auth.component.BorderedRoundedRect7
import com.smile.mypark.presentation.auth.component.MyParkLogo
import com.smile.mypark.presentation.auth.component.MyparkLoginButton
import mypark.composeapp.generated.resources.Res
import mypark.composeapp.generated.resources.ic_radio_off
import mypark.composeapp.generated.resources.ic_radio_on
import mypark.composeapp.generated.resources.login
import mypark.composeapp.generated.resources.login_id
import mypark.composeapp.generated.resources.login_pw
import mypark.composeapp.generated.resources.login_warning
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
internal fun LoginRoute(
    padding: PaddingValues,
    navigateToHome: () -> Unit,
    navigateToSignUp: () -> Unit,
    navigateToFindIdPw: () -> Unit,
    viewModel: AuthViewModel = koinViewModel()
) {
    val state by viewModel.viewState.collectAsStateWithLifecycle()

    LaunchedEffect(viewModel) {
        viewModel.effect.collect { eff ->
            when (eff) {
                AuthContract.AuthSideEffect.NavigateHome -> navigateToHome()
                AuthContract.AuthSideEffect.NavigateSignup -> navigateToSignUp()
                AuthContract.AuthSideEffect.NavigateFindIdPw -> navigateToFindIdPw()
                is AuthContract.AuthSideEffect.Toast -> { /* show toast */ }
            }

        }
    }

    LoginScreen(
        padding = padding,
        state = state,
        onEvent = viewModel::setEvent
    )
}

@Composable
private fun LoginScreen(
    padding: PaddingValues,
    state: AuthContract.AuthState,
    onEvent: (AuthContract.AuthEvent) -> Unit
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
            .windowInsetsPadding(WindowInsets.systemBars)
            .keyboardHide()
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

            BorderedRoundedRect7(
                value = state.id,
                onValueChange = { onEvent(AuthContract.AuthEvent.IdChanged(it)) },
                placeholder = stringResource(Res.string.login_id),
                textStyle = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = 13.toFixedSp(),
                    lineHeight = 17.toFixedSp(),
                    color = Black
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(horizontal = 30.dp)
            )

            Spacer(Modifier.height(5.dp))

            BorderedRoundedRect7(
                value = state.pw,
                onValueChange = { onEvent(AuthContract.AuthEvent.PwChanged(it)) },
                placeholder = stringResource(Res.string.login_pw),
                textStyle = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = 13.toFixedSp(),
                    lineHeight = 17.toFixedSp(),
                    color = Black
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(onDone = { onEvent(AuthContract.AuthEvent.ClickLogin) }),
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(horizontal = 30.dp)
            )

            Spacer(Modifier.height(7.dp))

            val canLogin = state.id.isNotBlank() && state.pw.isNotBlank()

            MyparkLoginButton(
                text = stringResource(Res.string.login),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(horizontal = 30.dp),
                onClick = { if (canLogin) onEvent(AuthContract.AuthEvent.ClickLogin) },
                enabled = canLogin
            )

            Spacer(Modifier.height(11.dp))

            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 30.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.noRippleSingleClickable { onEvent(AuthContract.AuthEvent.ToggleAutoLogin) }
                ) {
                    CustomRadioButton(
                        selected = state.autoLogin,
                        onClick = { onEvent(AuthContract.AuthEvent.ToggleAutoLogin) },
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
                    modifier = Modifier.noRippleSingleClickable { onEvent(AuthContract.AuthEvent.ToggleAutoIdLogin) }
                ) {
                    CustomRadioButton(
                        selected = state.autoIdLogin,
                        onClick = { onEvent(AuthContract.AuthEvent.ToggleAutoIdLogin) },
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
                    modifier = Modifier.clickable { onEvent(AuthContract.AuthEvent.ClickSignUp) }
                )
                Text(
                    text = "아이디/비밀번호 찾기",
                    style = MaterialTheme.typography.bodyLarge.copy(fontSize = 13.toFixedSp(), lineHeight = 15.toFixedSp()),
                    color = LightGray179,
                    modifier = Modifier.clickable { onEvent(AuthContract.AuthEvent.ClickFindIdPw) }
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
        state = AuthContract.AuthState(),
        onEvent = {}
    )
}